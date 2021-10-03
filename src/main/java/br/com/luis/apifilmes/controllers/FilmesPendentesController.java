package br.com.luis.apifilmes.controllers;

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
import br.com.luis.apifilmes.models.utils.IdiomaUtils;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

@RestController
@RequestMapping("/filmes/pendentes")
@EnableScheduling
public class FilmesPendentesController implements MetodosPadrao {
	private static Calculadora calculadora;
	private final Destino tipoDeConsulta = Destino.PENDENTES;
	private List<Filme> filmes = Mapeamento.getFilmes(tipoDeConsulta);

	@GetMapping("/random")
	public ResponseEntity<Filme> obterFilmeAleatorio() {
		int posicaoAleatoria = calculadora.getNumeroAleatorio(filmes.size());
		return ResponseEntity.ok(filmes.get(posicaoAleatoria));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Filme>> obterTodosOsFilmes() {
		return ResponseEntity.ok(filmes);
	}

	@GetMapping("/idioma")
	public ResponseEntity<List<Filme>> filtrarFilmePorIdioma(@RequestParam String idioma) {
		List<Filme> filmesVistosPorIdioma = filmes.stream()
				.filter(filme -> IdiomaUtils.filtrarPorIdioma(filme, idioma))
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesVistosPorIdioma);
	}

	@GetMapping("/last")
	public ResponseEntity<Filme> obterUltimoFilmeVisto() {
		return ResponseEntity.ok(filmes.get(filmes.size() - 1));
	}

	@GetMapping("/palavra")
	public ResponseEntity<List<Filme>> filtrarFilmePorPalavraChave(@RequestParam String palavra) {
		List<Filme> filmesEncontradosPorKeyword = FilmeUtils.buscarFilmePorPalavra(filmes, palavra);
		return ResponseEntity.ok(filmesEncontradosPorKeyword);
	}

	@GetMapping("/lancamento")
	public ResponseEntity<List<Filme>> buscarFilmePorAnoDeLancamento(@RequestParam int ano) {
		List<Filme> filmesFiltradosPorAnoDeLancamento = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorAnoDeLancamento(filme, ano))
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesFiltradosPorAnoDeLancamento);
	}

	@GetMapping("/ano")
	public ResponseEntity<List<Filme>> buscarFilmePorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		List<Filme> filmesFiltradosPorIntervaloDeAnos = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorIntervaloDeAnos(filme, de, ate))
				.collect(Collectors.toList());
        return ResponseEntity.ok(filmesFiltradosPorIntervaloDeAnos);
	}

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarLista() {
		filmes = Mapeamento.getFilmes(tipoDeConsulta);
	}
}
