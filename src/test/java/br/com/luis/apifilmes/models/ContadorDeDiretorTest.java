package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;

class ContadorDeDiretorTest {
	
	ContadorDeDiretor contador;
	List<Diretor> diretores;
	
	@BeforeEach
	void setUp() {
		contador = new ContadorDeDiretor();
		diretores = new ArrayList<>();
	}
	
	@Test
	void deveRetornarUmFilmeCasoHajaApenasUmDiretorNaListaEPossuaApenasUmFilme() {
		preencherListaDeDiretores("Sam Liu");

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, "Sam Liu");
		assertEquals(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos(), 1);
	}

	@Test
	void deveRetornarDoisFilmesParaCadaDiretorQuandoHouverMaisDeUmDiretorNaListaEAmbosTiveremDoisFilmes() {
		String[] nomes = {"Sam Liu", "Sam Liu", "Christopher Nolan", "Christopher Nolan"};
		preencherListaDeDiretores(nomes);

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomes);
		assertEquals(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos(), 2);
		assertEquals(diretoresRetornados.get(1).getQuantidadeDeFilmesVistos(), 2);
	}

	@Test
	void deveRetornarQuantidadeDeFilmesNula() {
		preencherListaDeDiretores("Sam Liu");

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, "Fulano de tal");
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos());
	}


	@Test
	void deveRetornarQuantidadeDeFilmesNaoNulaEUmaNula() {
		preencherListaDeDiretores("Sam Liu");
		diretores.add(new Diretor("Fulano de Tal"));

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, "Sam Liu");
		assertEquals(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos(), 1);
		assertNull(diretoresRetornados.get(1).getQuantidadeDeFilmesVistos());
	}

	@Test
	void deveRetornarListaVaziaSePassarListaVaziaComoArgumento() {
		List<Diretor> diretoresRetornados = contador.definirOcorrencias(List.of());
		assertEquals(diretoresRetornados, List.of());
	}

	@Test
	void deveRetornarZeroFilmesVistosSeNomeNaoForExatamenteIgual() {
		preencherListaDeDiretores("Sam Liu");

		String[] nomesNaoExatos = {"sam liu", "SAM LIU", "sAm LiU", "SAM liu"};
		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomesNaoExatos);
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos());
	}

	private void preencherListaDeDiretores(String... nomes) {
		Arrays.stream(nomes).forEach(nome -> diretores.add(new Diretor(nome)));
	}
}
