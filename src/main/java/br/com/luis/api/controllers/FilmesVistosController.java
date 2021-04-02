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

	@GetMapping("/month")
	public List<Filme> filtrarPorMes(@RequestParam(name = "m") int mes) {
		return filmes.stream().filter(filme -> filme.getMes().getNumeroDoMes() == mes).collect(Collectors.toList());
	}

	@GetMapping("/idioma")
	public List<Filme> filtrarPorIdioma(@RequestParam(name = "lang") String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getAbreviacao().contains(idioma))
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