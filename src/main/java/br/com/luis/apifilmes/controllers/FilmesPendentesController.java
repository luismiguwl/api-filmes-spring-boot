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

import br.com.luis.apifilmes.acessoadados.AcessoADados;
import br.com.luis.apifilmes.interfaces.ControllerDeFilme;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.utils.*;

import static br.com.luis.apifilmes.models.utils.FilmeUtils.*;

@RestController
@RequestMapping("/filmes/pendentes")
@EnableScheduling
public class FilmesPendentesController implements ControllerDeFilme<FilmePendente> {
	private final Destino destino = Destino.PENDENTES;
	private List<FilmePendente> filmes;
	
	private FilmesPendentesController() {
		filmes = new ArrayList<>();
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
		List<Filme> filmesEncontradosPorKeyword = buscarFilmePorPalavra(filmes, palavra);
		converterFilmesGenericosParaFilmesEspecificos(filmesEncontradosPorKeyword);
		return ResponseEntity.ok(filmes);
	}

	@GetMapping(value = "/lancamento", params = "ano")
	public ResponseEntity<List<FilmePendente>> buscarFilmePorAnoDeLancamento(@RequestParam int ano) {
		List<FilmePendente> filmesFiltradosPorAnoDeLancamento = filmes.stream()
				.filter(filme -> filme.getAnoDeLancamento() == ano)
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesFiltradosPorAnoDeLancamento);
	}

	@GetMapping(value = "/lancamento", params = {"de", "ate"})
	public ResponseEntity<List<FilmePendente>> buscarFilmePorAnoDeLancamento(@RequestParam int de, @RequestParam int ate) {
		List<FilmePendente> filmesFiltradosPorIntervaloDeAnos = filmes.stream()
				.filter(filme -> filme.anoDeLancamentoEstaEntre(de, ate))
				.collect(Collectors.toList());
        return ResponseEntity.ok(filmesFiltradosPorIntervaloDeAnos);
	}

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarLista() {
		AcessoADados acessoADados = new AcessoADados(destino);
		List<Filme> filmesNaoConvertidos = acessoADados.getFilmes();
		converterFilmesGenericosParaFilmesEspecificos(filmesNaoConvertidos);
	}
	
	public void converterFilmesGenericosParaFilmesEspecificos(List<Filme> filmesGenericos) {
		filmes.clear();
		filmesGenericos.stream()
			.forEach(filme -> filmes.add((FilmePendente) filme));
	}
}
