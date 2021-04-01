package br.com.luis.api.controllers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("lang={idioma}")
	public List<Filme> filtrarPorIdioma(@RequestParam String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getAbreviacao().equals(idioma))
				.collect(Collectors.toList());
	}

	@GetMapping("/month={mes}")
	public List<Filme> filtrarPorMes(@PathVariable int mes) {
		return filmes.stream().filter(filme -> filme.getMes().getNumeroDoMes() == mes).collect(Collectors.toList());
	}

	@GetMapping("/last")
	public Filme ultimo() {
		return filmes.get(filmes.size() - 1);
	}

	@GetMapping("/key={chave}")
	public List<Filme> filtrarPorPalavraChave(@PathVariable String chave) {
		System.out.println(chave);
		return FilmeUtils.buscarFilmePorPalavra(filmes, chave);
	}

	@GetMapping("/ano={ano}")
	public List<Filme> buscarPorAnoDeLancamento(@PathVariable int ano) {
		return filmes.stream().filter(filme -> filme.getAno() == ano).collect(Collectors.toList());
	}

	@GetMapping("/ano?{de}/{para}")
	public List<Filme> buscarPorIntervaloDeAnos(@PathParam(value = "de") int de, @PathParam(value = "para") int ate) {
		return filmes.stream().filter(filme -> filme.getAno() >= de && filme.getAno() <= ate)
				.collect(Collectors.toList());
	}
}
