package br.com.luis.apifilmes.controllers;

import static br.com.luis.apifilmes.models.utils.FilmeUtils.buscarFilmePorPalavra;
import static br.com.luis.apifilmes.utils.Calculadora.getNumeroAleatorio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

@RestController
@RequestMapping("/filmes/pendentes")
@EnableScheduling
public class FilmesPendentesController implements MetodosPadrao {
	private final TipoDeConsulta tipoDeConsulta = TipoDeConsulta.PENDENTES;
	private List<Filme> filmes = atualizarLista();

	@GetMapping("/random")
	public Filme random() {
		int posicaoAleatoria = getNumeroAleatorio(filmes.size());
		return filmes.get(posicaoAleatoria);
	}

	@GetMapping("/all")
	public List<Filme> all() {
		return filmes;
	}

	@GetMapping("/idioma")
	public List<Filme> filtrarPorIdioma(@RequestParam String idioma) {
		return filmes.stream()
				.filter(filme -> IdiomaUtils.filtrarPorIdioma(filme, idioma))
				.collect(Collectors.toList());
	}

	@GetMapping("/last")
	public Filme ultimo() {
		return filmes.get(filmes.size() - 1);
	}

	@GetMapping("/palavra")
	public List<Filme> filtrarPorPalavraChave(@RequestParam String palavra) {
		return buscarFilmePorPalavra(filmes, palavra);
	}

	@GetMapping("/lancamento")
	public List<Filme> buscarPorAnoDeLancamento(@RequestParam int ano) {
		return filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorAnoDeLancamento(filme, ano))
				.collect(Collectors.toList());
	}

	@GetMapping("/ano")
	public List<Filme> buscarPorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		return filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorIntervaloDeAnos(filme, de, ate))
				.collect(Collectors.toList());
	}

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public List<Filme> atualizarLista() {
		return Mapeamento.getFilmes(tipoDeConsulta);
	}
}
