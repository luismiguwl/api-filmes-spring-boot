package br.com.luis.apifilmes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.models.utils.*;
import br.com.luis.apifilmes.utils.*;

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
	
	@GetMapping("/diretores")
	public ResponseEntity<List<Diretor>> obterListaContendoTodosOsDiretores() {
		List<Diretor> listaContendoTodosOsDiretores = DiretorUtils.getAllDiretoresDistintos(filmes);
		return ResponseEntity.ok(listaContendoTodosOsDiretores);
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