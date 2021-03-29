package br.com.luis.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.Mes;
import br.com.luis.api.models.utils.IdiomaUtils;
import br.com.luis.api.models.utils.MesUtils;
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
		return filmes;
	}

	@GetMapping("/month={mes}")
	public List<Filme> filtrarPorMes(@PathVariable int mes) {
		return filmes.stream()
				.filter(filme -> filme.getMes().getNumeroDoMes() == mes)
				.collect(Collectors.toList());
	}

	@GetMapping("/lang={idioma}")
	public List<Filme> filtrarPorIdioma(@PathVariable String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getAbreviacao().equalsIgnoreCase(idioma))
				.collect(Collectors.toList());
	}

	@GetMapping("/idiomas")
	public List<String> listarIdiomasDistintos() {
		return IdiomaUtils.definirQuantidadeDeFilmesEmDeterminadoIdioma();
	}
	
	@GetMapping("/quantidadepormes")
	public List<String> listarQuantidadeDeFilmesVistosEmCadaMes() {
		return MesUtils.listarQuantidadeDeCadaMes();
	}
	
}