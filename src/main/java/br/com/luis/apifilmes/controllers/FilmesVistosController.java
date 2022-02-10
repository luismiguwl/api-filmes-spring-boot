package br.com.luis.apifilmes.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.arquivo.EscritorDeCSV;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

import static br.com.luis.apifilmes.models.DestinoAtual.*;

@RestController
@RequestMapping("/**/filmes/vistos")
public class FilmesVistosController implements MetodosPadrao<FilmeVisto> {
	private Destino destino = Destino.obterDestinoBaseadoNoAnoAtual();
	public List<FilmeVisto> filmes;
	private Mapeamento mapeamento;

	@GetMapping("/random")
	public ResponseEntity<FilmeVisto> obterFilmeAleatorio() {
		int posicaoAleatoria = GeradorDeNumeroAleatorio.gerar(filmes.size());
		return ResponseEntity.ok(filmes.get(posicaoAleatoria));
	}

	@GetMapping("/all")
	public ResponseEntity<List<FilmeVisto>> obterTodosOsFilmes() {
		return ResponseEntity.ok(filmes);
	}

	@GetMapping("/last")
	public ResponseEntity<FilmeVisto> obterUltimoFilmeVisto() {
		if (filmes.isEmpty()) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.ok(filmes.get(filmes.size() - 1));
	}

	@GetMapping("/palavra")
	public ResponseEntity<List<FilmeVisto>> filtrarFilmePorPalavraChave(@RequestParam String palavra) {
		List<FilmeVisto> filmesEncontradosPorKeyword = (List<FilmeVisto>) FilmeUtils.buscarFilmePorPalavra(filmes, palavra);
		return ResponseEntity.ok(filmesEncontradosPorKeyword);
	}

	@GetMapping("/lancamento")
	public ResponseEntity<List<FilmeVisto>> buscarFilmePorAnoDeLancamento(@RequestParam int ano) {
		List<FilmeVisto> filmesFiltradosPorAnoDeLancamento = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorAnoDeLancamento(filme, ano)).collect(Collectors.toList());
		return ResponseEntity.ok(filmesFiltradosPorAnoDeLancamento);
	}

	@GetMapping("/ano")
	public ResponseEntity<List<FilmeVisto>> buscarFilmePorIntervaloDeAnos(@RequestParam int de, @RequestParam int ate) {
		List<FilmeVisto> filmesFiltradosPorIntervaloDeAnos = filmes.stream()
				.filter(filme -> FilmeUtils.buscarPorIntervaloDeAnos(filme, de, ate)).collect(Collectors.toList());
		return ResponseEntity.ok(filmesFiltradosPorIntervaloDeAnos);
	}

	@GetMapping("/ranking/diretores")
	public ResponseEntity<List<Diretor>> obterListaDeDiretoresComMaisFilmesVistos(@RequestParam int top) {
		List<Diretor> diretoresComMaisFilmes = DiretorUtils
				.filtrarDiretoresComMaisFilmes(mapeamento.getDadosDaColuna(destino, Coluna.DIRETOR), top);
		return ResponseEntity.ok(diretoresComMaisFilmes);
	}

	@GetMapping("/idiomas")
	public ResponseEntity<List<String>> obterListaDeIdiomas() {
		List<String> idiomas = new ArrayList<>();

		idiomas = filmes.stream().map(filme -> filme.getIdioma().getNome()).distinct().collect(Collectors.toList());

		return ResponseEntity.ok(idiomas);
	}

	@GetMapping("/diretores")
	public ResponseEntity<List<String>> obterListaDeDiretoresDistintos() {
		List<String> nomeDosDiretores = new ArrayList<>();

		for (FilmeVisto filme : filmes) {
			for (Diretor diretor : filme.getDiretores()) {
				nomeDosDiretores.add(diretor.getNome());
			}
		}

		nomeDosDiretores = nomeDosDiretores.stream().sorted().distinct().collect(Collectors.toList());
		return ResponseEntity.ok(nomeDosDiretores);
	}

	@GetMapping("/plataformas")
	public ResponseEntity<List<String>> obterListaDePlataformas() {
		List<String> plataformasString = new ArrayList<>();
		Plataforma[] plataformas = Plataforma.values();

		for (Plataforma plataforma : plataformas) {
			plataformasString.add(plataforma.getNome());
		}

		return ResponseEntity.ok(plataformasString);
	}

	@GetMapping("/generos")
	public ResponseEntity<List<String>> obterListaDeGeneros() {
		List<String> generos = obterListaDeGenerosDistintos();
		return ResponseEntity.ok(generos);
	}

	private List<String> obterListaDeGenerosDistintos() {
		List<String> todosOsGeneros = new ArrayList<>();

		for (FilmeVisto filme : filmes) {
			List<Genero> generos = filme.getGeneros();
			generos.stream().map(genero -> genero.getNome()).distinct().forEach(genero -> todosOsGeneros.add(genero));
		}

		return todosOsGeneros.stream().distinct().sorted().collect(Collectors.toList());
	}

	@PostMapping(value = "/inserir")
	public void inserirFilme(@RequestBody FilmeVisto filme) {
		EscritorDeCSV escritor = new EscritorDeCSV(destino);
		escritor.escreverFilmeNoArquivoCSV(filme);
	}

	@ModelAttribute
	private void definirDados() {
		PathAtual path = new PathAtual();
		DestinoAtual destinoAtual = new DestinoAtual(path);

		if (!destinoAtual.validarAnoDaRequisicaoAtual()) {
			int ano = destinoAtual.obterAnoDaRequisicao();
			int anoAtual = new AnoAtual().get();
			throw new IllegalArgumentException(String.format("%d não é um ano válido. Informe um ano entre 2021 e %d", ano, anoAtual));
		}

		destino = destinoAtual.getDestino();
		atualizarListaDeFilmes();
	}

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarListaDeFilmes() {
		mapeamento = new Mapeamento(destino);
		List<Filme> filmesGenericos = mapeamento.getFilmes();
		converterFilmesGenericosParaFilmesEspecificos(filmesGenericos);
	}

	public void converterFilmesGenericosParaFilmesEspecificos(List<Filme> filmesGenericos) {
		filmes = new ArrayList<>();

		for (Filme filme : filmesGenericos) {
			if (filme instanceof FilmeVisto) {
				filmes.add((FilmeVisto) filme);
			}
		}
	}

}