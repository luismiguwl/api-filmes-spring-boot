package br.com.luis.api.controllers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.Mes;
import br.com.luis.api.utils.Mapeamento;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	private List<Filme> filmes = Mapeamento.converterLinhaEmObjeto();

	@GetMapping("/random")
	public Filme random() {
		int posicaoAleatoria = new Random().nextInt(filmes.size());
		return filmes.get(posicaoAleatoria);
	}

	@GetMapping("/all")
	public List<Filme> allMovies() {
		return Mapeamento.converterLinhaEmObjeto();
	}

	@GetMapping("/month={mes}")
	public List<Filme> filtrarPorMes(@PathVariable("mes") int mes) {
		return filmes.stream().filter(filme -> filme.getMes() == Mes.values()[mes - 1]).collect(Collectors.toList());
	}
	
	@GetMapping("/lang={idioma}")
	public List<Filme> filtrarPorIdioma(@PathVariable("idioma") String idioma) {
		return filmes.stream()
				.filter(filme -> filme.getIdioma().getAbreviacao().equalsIgnoreCase(idioma))
				.collect(Collectors.toList());
	}

}