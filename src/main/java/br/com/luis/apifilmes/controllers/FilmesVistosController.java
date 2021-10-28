package br.com.luis.apifilmes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

@RestController
@RequestMapping("/filmes/vistos")
public class FilmesVistosController implements MetodosPadrao {
	private static Calculadora calculadora;
	private final Destino tipoDeConsulta = Destino.VISTOS;
	public List<Filme> filmes = Mapeamento.getFilmes(tipoDeConsulta);

	@GetMapping("/random")
	public ResponseEntity<Filme> obterFilmeAleatorio() {
		int posicaoAleatoria = calculadora.getNumeroAleatorio(filmes.size());
		return ResponseEntity.ok(filmes.get(posicaoAleatoria));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Filme>> obterTodosOsFilmes() {
		return ResponseEntity.ok(filmes);
	}

	@GetMapping("/month")
	public ResponseEntity<List<Filme>> filtrarFilmePorMes(@RequestParam int mes) {
		List<Filme> filmesVistosNoMes = filmes.stream()
				.filter(filme -> MesUtils.filtrarPorMes(filme, mes))
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesVistosNoMes);
	}

	@GetMapping("/idioma")
	public ResponseEntity<List<Filme>> filtrarFilmePorIdioma(@RequestParam String idioma) {
		List<Filme> filmesVistosPorIdioma = filmes.stream()
				.filter(filme -> IdiomaUtils.filtrarPorIdioma(filme, idioma))
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesVistosPorIdioma);
	}

	@GetMapping("/idiomas")
	public ResponseEntity<List<String>> listarQuantidadeDeFilmesEmCadaIdioma() {
		List<String> quantidadeDeFilmesPorIdioma = IdiomaUtils.definirQuantidadeDeFilmesEmDeterminadoIdioma();
		return ResponseEntity.ok(quantidadeDeFilmesPorIdioma);
	}

	@GetMapping("/quantidadepormes")
	public ResponseEntity<List<String>> listarQuantidadeDeFilmesVistosEmCadaMes() {
		List<String> quantidadeDeFilmesVistosEmCadaMes = MesUtils.listarQuantidadeDeCadaMes();
		return ResponseEntity.ok(quantidadeDeFilmesVistosEmCadaMes);
	}

	@GetMapping("/last")
	public ResponseEntity<Filme> obterUltimoFilmeVisto() {
		return ResponseEntity.ok(filmes.get(filmes.size() - 1));
	}

	@GetMapping("/palavra")
	public ResponseEntity<List<Filme>> filtrarFilmePorPalavraChave(@RequestParam String palavra) {
		List<Filme> filmesEncontradosPorKeyword = FilmeUtils.buscarFilmePorPalavra(filmes, palavra);
		return ResponseEntity.ok(filmesEncontradosPorKeyword);
	}

	@GetMapping("/lancamento")
	public ResponseEntity<List<Filme>> buscarFilmePorAnoDeLancamento(@RequestParam int ano) {
		List<Filme> filmesFiltradosPorAnoDeLancamento = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorAnoDeLancamento(filme, ano))
				.collect(Collectors.toList());
		return ResponseEntity.ok(filmesFiltradosPorAnoDeLancamento);
	}

	@GetMapping("/ano")
	public ResponseEntity<List<Filme>> buscarFilmePorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		List<Filme> filmesFiltradosPorIntervaloDeAnos = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorIntervaloDeAnos(filme, de, ate))
				.collect(Collectors.toList());
        return ResponseEntity.ok(filmesFiltradosPorIntervaloDeAnos);
	}

	@GetMapping("/ranking/diretores")
	public ResponseEntity<List<Diretor>> obterListaDeDiretoresComMaisFilmesVistos(@RequestParam int top) {
		List<Diretor> diretoresComMaisFilmes = DiretorUtils.filtrarDiretoresComMaisFilmes(filmes, top);
		return ResponseEntity.ok(diretoresComMaisFilmes);
	}
	
	@GetMapping("/diretores")
	public ResponseEntity<List<Diretor>> obterListaContendoTodosOsDiretores() {
		List<Diretor> listaContendoTodosOsDiretores = DiretorUtils.getAllDiretoresDistintos(filmes);
		return ResponseEntity.ok(listaContendoTodosOsDiretores);
	}
	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarLista() {
		filmes = Mapeamento.getFilmes(tipoDeConsulta);
	} 
}