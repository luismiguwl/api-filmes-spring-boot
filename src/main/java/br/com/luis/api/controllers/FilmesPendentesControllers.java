package br.com.luis.api.controllers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.MetodosPadrao;
import br.com.luis.api.models.utils.IdiomaUtils;
import br.com.luis.api.utils.Mapeamento;

@RestController
@RequestMapping("/filmes/pendentes")
public class FilmesPendentesControllers extends MetodosPadrao {

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

	@GetMapping("lang={idioma}")
	public List<Filme> filtrarPorIdioma(@RequestParam String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getAbreviacao().equals(idioma))
				.collect(Collectors.toList());
	}

	@GetMapping("/idiomas")
	public List<String> listarQuantidadeDeFilmesEmCadaIdioma() {
		return IdiomaUtils.definirQuantidadeDeFilmesEmDeterminadoIdioma();
	}

	@GetMapping("/month={mes}")
	public List<Filme> filtrarPorMes(@PathVariable int mes) {
		return filmes.stream().filter(filme -> filme.getMes().getNumeroDoMes() == mes).collect(Collectors.toList());
	}

	@GetMapping("/last")
	public Filme ultimo() {
		return filmes.get(filmes.size() - 1);
	}

	@Override
	public List<Filme> filtrarPorPalavraChave(String palavra) {
		return null;
	}

}
