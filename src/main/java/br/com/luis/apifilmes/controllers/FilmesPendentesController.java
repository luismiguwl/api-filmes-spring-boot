package br.com.luis.apifilmes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.models.utils.FilmeUtils;
import br.com.luis.apifilmes.utils.*;

@RestController
@RequestMapping("/filmes/pendentes")
@EnableScheduling
public class FilmesPendentesController implements MetodosPadrao<FilmePendente> {
	private final Destino destino = Destino.PENDENTES;
	private List<FilmePendente> filmes;
	
	public FilmesPendentesController() {
		atualizarLista();
	}
	
	@GetMapping("/random")
	public ResponseEntity<FilmePendente> obterFilmeAleatorio() {
		int posicaoAleatoria = GeradorDeNumeroAleatorio.gerar(filmes.size());
		return ResponseEntity.ok(filmes.get(posicaoAleatoria));
	}

	@GetMapping("/all")
	public ResponseEntity<List<FilmePendente>> obterTodosOsFilmes() {
		return ResponseEntity.ok(filmes);
	}

	@GetMapping("/last")
	public ResponseEntity<FilmePendente> obterUltimoFilmeVisto() {
		return ResponseEntity.ok(filmes.get(filmes.size() - 1));
	}

	@GetMapping("/palavra")
	public ResponseEntity<List<FilmePendente>> filtrarFilmePorPalavraChave(@RequestParam String palavra) {
		List<FilmePendente> filmesEncontradosPorKeyword = (List<FilmePendente>) FilmeUtils.buscarFilmePorPalavra(filmes, palavra);
		return ResponseEntity.ok(filmesEncontradosPorKeyword);
	}

	@GetMapping("/lancamento")
	public ResponseEntity<List<FilmePendente>> buscarFilmePorAnoDeLancamento(@RequestParam int ano) {
		List<FilmePendente> filmesFiltradosPorAnoDeLancamento = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorAnoDeLancamento(filme, ano))
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesFiltradosPorAnoDeLancamento);
	}

	@GetMapping("/ano")
	public ResponseEntity<List<FilmePendente>> buscarFilmePorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		List<FilmePendente> filmesFiltradosPorIntervaloDeAnos = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorIntervaloDeAnos(filme, de, ate))
				.collect(Collectors.toList());
        return ResponseEntity.ok(filmesFiltradosPorIntervaloDeAnos);
	}

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarLista() {
		Mapeamento mapeamento = new Mapeamento(destino);
		List<Filme> filmesNaoConvertidos = mapeamento.getFilmes();
		converterFilmesGenericosParaFilmesEspecificos(filmesNaoConvertidos);
	}
	
	public void converterFilmesGenericosParaFilmesEspecificos(List<Filme> filmesGenericos) {
		filmes = new ArrayList<>();
		
		for (Filme filme : filmesGenericos) {
			if (filme instanceof FilmePendente) {
				filmes.add((FilmePendente) filme);
			}
		}
	}
}
