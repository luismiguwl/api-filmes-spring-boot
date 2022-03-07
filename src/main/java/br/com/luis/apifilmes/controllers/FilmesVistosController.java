package br.com.luis.apifilmes.controllers;

import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;

import br.com.luis.apifilmes.acessoadados.*;
import br.com.luis.apifilmes.arquivo.*;
import br.com.luis.apifilmes.exceptions.*;
import br.com.luis.apifilmes.interfaces.*;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

import static br.com.luis.apifilmes.models.utils.FilmeUtils.*;

@RestController
@RequestMapping(value = "/**/filmes/vistos")
public class FilmesVistosController implements ControllerDeFilme<FilmeVisto> {
	private Destino destino;
	private List<FilmeVisto> filmes;
	private AcessoADados acessoADados;
	
	private FilmesVistosController() {
		filmes = new ArrayList<>();
		acessoADados = new AcessoADados(destino);
		destino = Destino.obterDestinoBaseadoNoAnoAtual();
		
		atualizarListaDeFilmes();
	}

	@GetMapping(value = "/random")
	public ResponseEntity<FilmeVisto> obterFilmeAleatorio() {
		if (filmes.isEmpty()) {
			return ResponseEntity.ok().build();
		}

		int posicaoAleatoria = GeradorDeNumeroAleatorio.gerar(filmes.size());
		return ResponseEntity.ok(filmes.get(posicaoAleatoria));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<FilmeVisto>> obterTodosOsFilmes() {
		return ResponseEntity.ok(filmes);
	}

	@GetMapping(value = "/last")
	public ResponseEntity<FilmeVisto> obterUltimoFilmeVisto() {
		if (filmes.isEmpty()) {
			return ResponseEntity.ok().build();
		}

		FilmeVisto filme = filmes.get(filmes.size() - 1);
		return ResponseEntity.ok(filme);
	}

	@GetMapping(value = "/palavra", params = "palavra")
	public ResponseEntity<List<FilmeVisto>> filtrarFilmePorPalavraChave(@RequestParam String palavra) {
		List<Filme> filmesEncontradosPorKeyword = buscarFilmePorPalavra(filmes, palavra);
		converterFilmesGenericosParaFilmesEspecificos(filmesEncontradosPorKeyword);
		
		return ResponseEntity.ok(filmes);
	}

	@GetMapping(value = "/lancamento", params = "ano")
	public ResponseEntity<List<FilmeVisto>> buscarFilmePorAnoDeLancamento(@RequestParam int ano) {
		List<FilmeVisto> filmesFiltradosPorAnoDeLancamento = filmes.stream()
				.filter(filme -> filme.getAnoDeLancamento() == ano)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(filmesFiltradosPorAnoDeLancamento);
	}

	@GetMapping(value = "/lancamento", params = {"de", "ate"})
	public ResponseEntity<List<FilmeVisto>> buscarFilmePorAnoDeLancamento(@RequestParam int de, @RequestParam int ate) {
		List<FilmeVisto> filmesFiltradosPorIntervaloDeAnos = filmes.stream()
				.filter(filme -> filme.anoDeLancamentoEstaEntre(de, ate))
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(filmesFiltradosPorIntervaloDeAnos);
	}

	@GetMapping(value = "/ranking/diretores", params = "top")
	public ResponseEntity<List<Diretor>> obterListaDeDiretoresComMaisFilmesVistos(@RequestParam int top) {
		List<Diretor> diretores = new ArrayList<>();

		for (Filme filme : filmes) {
			for (Diretor diretor : filme.getDiretores()) {
				diretores.add(diretor);
			}
		}
		
		diretores = DiretorUtils.filtrarDiretoresComMaisFilmes(diretores, top);
		
		return ResponseEntity.ok(diretores);
	}

	@GetMapping(value = "/idiomas")
	public ResponseEntity<Set<String>> obterListaDeIdiomas() {
		Set<String> idiomas = new TreeSet<>();

		filmes.forEach(filme -> idiomas.add(filme.getIdioma().getNome()));
		
		return ResponseEntity.ok(idiomas);
	}

	@GetMapping(value = "/diretores")
	public ResponseEntity<Set<String>> obterListaDeDiretoresDistintos() {
		Set<String> nomeDosDiretores = new TreeSet<>();

		for (FilmeVisto filme : filmes) {
			for (Diretor diretor : filme.getDiretores()) {
				nomeDosDiretores.add(diretor.getNome());
			}
		}

		return ResponseEntity.ok(nomeDosDiretores);
	}

	@GetMapping(value = "/plataformas")
	public ResponseEntity<List<String>> obterListaDePlataformas() {
		List<String> plataformasString = new ArrayList<>();
		Plataforma[] plataformas = Plataforma.values();

		for (Plataforma plataforma : plataformas) {
			plataformasString.add(plataforma.getNomes()[0]);
		}

		return ResponseEntity.ok(plataformasString);
	}

	@GetMapping(value = "/generos")
	public ResponseEntity<Set<String>> obterListaDeGeneros() {
		Set<String> generos = new TreeSet<>();
		
		for (Filme filme : filmes) {
			String[] generosDoFilme = filme.getGeneros()
					.stream()
					.map(Genero::getNome)
					.collect(Collectors.toList())
					.toArray(new String[0]);
			
			generos.addAll(Arrays.asList(generosDoFilme));
		}
		
		return ResponseEntity.ok(generos);
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
			throw new AnoDaRequisicaoInvalidoException(String.format("%d não é um ano válido. Informe um ano entre 2021 e %d", ano, anoAtual));
		}

		if (!destino.equals(destinoAtual.getDestino())) {
			destino = destinoAtual.getDestino();
			atualizarListaDeFilmes();
		}
	}

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarListaDeFilmes() {
		acessoADados = new AcessoADados(destino);
		List<Filme> filmesGenericos = acessoADados.getFilmes();
		converterFilmesGenericosParaFilmesEspecificos(filmesGenericos);
	}

	public void converterFilmesGenericosParaFilmesEspecificos(List<Filme> filmesGenericos) {
		filmes.clear();
		filmesGenericos.stream()
			.forEach(filme -> filmes.add((FilmeVisto) filme));
	}

}