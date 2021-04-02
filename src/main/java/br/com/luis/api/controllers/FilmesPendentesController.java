package br.com.luis.api.controllers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.MetodosPadrao;
import br.com.luis.api.models.utils.FilmeUtils;
import br.com.luis.api.utils.Mapeamento;

@RestController
@RequestMapping("/filmes/pendentes")
public class FilmesPendentesController extends MetodosPadrao {

	private List<Filme> filmes = Mapeamento.getFilmesPendentes();

	@GetMapping("/all")
	public List<Filme> all() {
		return filmes;
	}

	@GetMapping("/random")
	public Filme random() {
		int numeroAleatorio = new Random().nextInt(filmes.size());
		return filmes.get(numeroAleatorio);
	}

	@GetMapping("/month")
	public List<Filme> filtrarPorMes(@RequestParam(name = "m") int mes) {
		return filmes.stream().filter(filme -> filme.getMes().getNumeroDoMes() == mes).collect(Collectors.toList());
	}

	@GetMapping("/idioma")
	public List<Filme> filtrarPorIdioma(@RequestParam(name = "lang") String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getAbreviacao().contains(idioma))
				.collect(Collectors.toList());
	}

	@GetMapping("/last")
	public Filme ultimo() {
		return filmes.get(filmes.size() - 1);
	}

	@GetMapping("/palavra")
	public List<Filme> filtrarPorPalavraChave(@RequestParam(name = "key") String chave) {
		return FilmeUtils.buscarFilmePorPalavra(filmes, chave);
	}

	@GetMapping("/lancamento")
	public List<Filme> buscarPorAnoDeLancamento(@RequestParam(name = "ano") int ano) {
		return filmes.stream().filter(filme -> filme.getAno() == ano).collect(Collectors.toList());
	}

	@GetMapping("/ano")
	public List<Filme> buscarPorIntervaloDeAnos(@RequestParam(name = "de") int de,
			@RequestParam(name = "ate") int ate) {
		return filmes.stream().filter(filme -> filme.getAno() >= de && filme.getAno() <= ate)
				.collect(Collectors.toList());
	}
}
