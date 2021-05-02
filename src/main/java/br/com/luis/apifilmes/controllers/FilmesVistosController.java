package br.com.luis.apifilmes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

@RestController
@RequestMapping("/filmes/vistos")
public class FilmesVistosController implements MetodosPadrao {
	private final TipoDeConsulta tipoDeConsulta = TipoDeConsulta.VISTOS;
	public List<Filme> filmes = Mapeamento.getFilmes(tipoDeConsulta);

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
				.filter(filme -> MesUtils.filtrarPorMes(filme, mes))
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
				.filter(filme -> FilmeUtils.buscarPorAnoDeLancamento(filme, ano))
				.collect(Collectors.toList());
	}

	@GetMapping("/ano")
	public List<Filme> buscarPorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		return filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorIntervaloDeAnos(filme, de, ate))
				.collect(Collectors.toList());
	}

	@GetMapping("/quantidadeporidioma")
	public List<String> quantidadeDeFilmesPorGenero() {
		return GeneroUtils.getTodosOsGeneros();
	}
	
	@GetMapping("/analise")
	public List<String> getSomaDaDuracaoDeTodosOsFilmes() {
		return Analise.getSomaDaDuracaoDeTodosOsFilmes(filmes);
	}

	@GetMapping("/ranking/diretores")
	public List<String> getTopDiretores(@RequestParam int top) {
		return DiretorUtils.filtrarDiretoresComMaisFilmes(filmes, top);
	}
	
	@GetMapping("/diretores")
	public List<Diretor> getDiretores() {
		return DiretorUtils.getAllDiretores(filmes);
	}
	
	@GetMapping("/generos")
	public List<Genero> getGeneros() {
		return GeneroUtils.getAllGeneros(filmes);
	}
	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void atualizarLista() {
		filmes = Mapeamento.getFilmes(tipoDeConsulta);
	}

}