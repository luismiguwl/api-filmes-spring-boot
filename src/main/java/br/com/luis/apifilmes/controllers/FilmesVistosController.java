package br.com.luis.apifilmes.controllers;

import java.util.ArrayList;
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

import br.com.luis.apifilmes.arquivo.Arquivo;
import br.com.luis.apifilmes.models.DestinoAtual;
import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Genero;
import br.com.luis.apifilmes.models.MetodosPadrao;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.models.enums.Plataforma;
import br.com.luis.apifilmes.models.utils.DiretorUtils;
import br.com.luis.apifilmes.models.utils.FilmeUtils;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

@RestController
@RequestMapping("/**/filmes/vistos")
public class FilmesVistosController implements MetodosPadrao {
	private Calculadora calculadora = Calculadora.get();
	private Destino destino = Destino.VISTOS_EM_2021;
	public List<Filme> filmes;

	@GetMapping("/random")
	public ResponseEntity<Filme> obterFilmeAleatorio() {
		int posicaoAleatoria = calculadora.getNumeroAleatorio(filmes.size());
		return ResponseEntity.ok(filmes.get(posicaoAleatoria));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Filme>> obterTodosOsFilmes() {
		System.out.println("requisicao pro metodo /all");
		return ResponseEntity.ok(filmes);
	}

	@GetMapping("/last")
	public ResponseEntity<Filme> obterUltimoFilmeVisto() {
		if (filmes.isEmpty()) {
			return ResponseEntity.ok().build();
		}
		
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
		List<Diretor> diretoresComMaisFilmes = DiretorUtils.filtrarDiretoresComMaisFilmes(Mapeamento.getDadosDaColuna(destino, Coluna.DIRETOR), top);
		return ResponseEntity.ok(diretoresComMaisFilmes);
	}
	
	@GetMapping("/idiomas")
	public ResponseEntity<List<String>> obterListaDeIdiomas() {
		List<String> idiomas = new ArrayList<>();

		idiomas = filmes.stream()
						.map(filme -> filme.getIdioma().getNome())
						.distinct()
						.collect(Collectors.toList());

		return ResponseEntity.ok(idiomas);
	}

	@GetMapping("/diretores")
	public ResponseEntity<List<String>> obterListaDeDiretoresDistintos() {
		List<String> nomeDosDiretores = new ArrayList<>();

		for (Filme filme : filmes) {
			for (Diretor diretor : filme.getDiretores()) {
				nomeDosDiretores.add(diretor.getNome());
			}
		}

		nomeDosDiretores = nomeDosDiretores
							.stream()
							.sorted()
							.distinct()
							.collect(Collectors.toList());
		return ResponseEntity.ok(nomeDosDiretores);
	}

	@GetMapping("/plataformas")
	public ResponseEntity<List<String>> obterListaDePlataformas() {
		List<String> plataformasString = new ArrayList<>();
		Plataforma[] plataformas = Plataforma.values();

		for (Plataforma plataforma : plataformas) {
			plataformasString.add(plataforma.getPlataforma());
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

		for (Filme filme : filmes) {
			List<Genero> generos = filme.getGeneros();
			generos.stream()
					.map(genero -> genero.getNome())
					.distinct()
					.forEach(genero -> todosOsGeneros.add(genero));
		}

		return todosOsGeneros.stream().distinct().sorted().collect(Collectors.toList());
	}

	@PostMapping(value = "/inserir")
	public void inserirFilme(@RequestBody Filme filme) {
		Arquivo.escreverFilmeNoArquivoCSV(filme, destino);
	}
	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void atualizarListaDeFilmes() {
		filmes = Mapeamento.getFilmes(destino);
	}
	
	@ModelAttribute
	private void definirDados() {
		String pathAtual = DestinoAtual.getPathAtual();

		if (!pathAtualPossuiAnoValido(pathAtual)) {
			throw new IllegalArgumentException("Ano inválido!");
		}

		destino = DestinoAtual.getDestino();
		atualizarListaDeFilmes();
	}

	private boolean pathAtualPossuiAnoValido(String pathAtual) {
		String ano = pathAtual.split("/")[1].trim();
		return ano.equals("2021") || ano.equals("2022");
	}
	
}