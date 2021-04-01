package br.com.luis.api.controllers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.MetodosPadrao;
import br.com.luis.api.models.utils.FilmeUtils;
import br.com.luis.api.models.utils.IdiomaUtils;
import br.com.luis.api.models.utils.MesUtils;
import br.com.luis.api.utils.Mapeamento;

@RestController
@RequestMapping("/filmes/vistos")
public class FilmesVistosController extends MetodosPadrao {
	private List<Filme> filmes = Mapeamento.getFilmesVistos();

	@GetMapping("/random")
	public Filme random() {
		int posicaoAleatoria = new Random().nextInt(filmes.size());
		return filmes.get(posicaoAleatoria);
	}

	@GetMapping("/all")
	public List<Filme> all() {
		return filmes;
	}

	@GetMapping("/month={mes}")
	public List<Filme> filtrarPorMes(@PathVariable int mes) {
		return filmes.stream().filter(filme -> filme.getMes().getNumeroDoMes() == mes).collect(Collectors.toList());
	}

	@GetMapping("/lang={idioma}")
	public List<Filme> filtrarPorIdioma(@PathVariable String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getAbreviacao().equalsIgnoreCase(idioma))
				.collect(Collectors.toList());
	}

	@GetMapping("/idiomas")
	public List<String> listarQuantidadeDeFilmesEmCadaIdioma() {
		return IdiomaUtils.definirQuantidadeDeFilmesEmDeterminadoIdioma();
	}

	@GetMapping("/quantidadepormes")
	public List<String> listarQuantidadeDeFilmesVistosEmCadaMes() {
		return MesUtils.listarQuantidadeDeCadaMes();
	}

	@GetMapping("/last")
	public Filme ultimo() {
		return filmes.get(filmes.size() - 1);
	}

	@GetMapping("/key={chave}")
	public List<Filme> filtrarPorPalavraChave(@PathVariable String chave) {
		return FilmeUtils.buscarFilmePorPalavra(filmes, chave);
	}

	@GetMapping("/ano={ano}")
	public List<Filme> buscarPorAnoDeLancamento(@PathVariable int ano) {
		return filmes.stream().filter(filme -> filme.getAno() == ano).collect(Collectors.toList());
	}

	@GetMapping("/ano/{de}/{ate}")
	public List<Filme> buscarPorIntervaloDeAnos(@PathVariable int de, @PathVariable int ate) {
		return filmes.stream().filter(filme -> filme.getAno() >= de && filme.getAno() <= ate)
				.collect(Collectors.toList());
	}

}