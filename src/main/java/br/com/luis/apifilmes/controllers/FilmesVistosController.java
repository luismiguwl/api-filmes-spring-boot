package br.com.luis.apifilmes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

@RestController
@RequestMapping("/filmes/vistos")
public class FilmesVistosController extends MetodosPadrao {
	private List<Filme> filmes = Mapeamento.getFilmes(TipoDeConsulta.VISTOS);

	@GetMapping("/random")
	public Filme random() {
		int posicaoAleatoria = Calculadora.getNumeroAleatorio(filmes.size());
		return filmes.get(posicaoAleatoria);
	}

	@GetMapping("/all")
	public List<Filme> all() {
		return filmes;
	}

	@GetMapping("/month")
	public List<Filme> filtrarPorMes(@RequestParam int mes) {
		return filmes.stream()
				.filter(filme -> filme.getMes().getNumeroDoMes() == mes)
				.collect(Collectors.toList());
	}

	@GetMapping("/idioma")
	public List<Filme> filtrarPorIdioma(@RequestParam String idioma) {
		return filmes.stream()
				.filter(filme -> IdiomaUtils.filtrarPorIdioma(filme, idioma))
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

	@GetMapping("/palavra")
	public List<Filme> filtrarPorPalavraChave(@RequestParam String palavra) {
		return FilmeUtils.buscarFilmePorPalavra(filmes, palavra);
	}

	@GetMapping("/lancamento")
	public List<Filme> buscarPorAnoDeLancamento(@RequestParam int ano) {
		return filmes.stream()
				.filter(filme -> filme.getAno() == ano)
				.collect(Collectors.toList());
	}

	@GetMapping("/ano")
	public List<Filme> buscarPorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		return filmes.stream()
				.filter(filme -> filme.getAno() >= de && filme.getAno() <= ate)
				.collect(Collectors.toList());
	}

	@GetMapping("/quantidadeporidioma")
	public List<String> quantidadeDeFilmesPorGenero() {
		return GeneroUtils.getTodosOsGeneros();
	}
}