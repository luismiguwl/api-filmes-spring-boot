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

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, new String[]{"Sam Liu"}, new String[]{});
		assertEquals(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos(), 1);
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesPendentes());
	}

	@Test
	void deveRetornarDoisFilmesParaCadaDiretorQuandoHouverMaisDeUmDiretorNaListaEAmbosTiveremDoisFilmes() {
		String[] nomes = {"Sam Liu", "Sam Liu", "Christopher Nolan", "Christopher Nolan"};
		preencherListaDeDiretores(nomes);

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomes, "Christopher Nolan");
		assertEquals(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos(), 2);
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesPendentes());
		assertEquals(diretoresRetornados.get(2).getQuantidadeDeFilmesVistos(), 2);
		assertEquals(diretoresRetornados.get(2).getQuantidadeDeFilmesPendentes(), 1);
	}

	@Test
	void deveRetornarQuantidadeDeFilmesNula() {
		preencherListaDeDiretores("Sam Liu");

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, new String[]{"Fulano de tal"});
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos());
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesPendentes());
	}

	@Test
	void deveRetornarQuantidadeDeFilmesNaoNulaEUmaNula() {
		preencherListaDeDiretores("Sam Liu");
		diretores.add(new Diretor("Fulano de Tal"));

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, new String[]{"Sam Liu"}, new String[]{});
		assertEquals(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos(), 1);
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesPendentes());
		assertNull(diretoresRetornados.get(1).getQuantidadeDeFilmesVistos());
		assertNull(diretoresRetornados.get(1).getQuantidadeDeFilmesPendentes());
	}
	
	@Test
	void deveRetornarZeroFilmesVistosSeNomeNaoForExatamenteIgual() {
		preencherListaDeDiretores("Sam Liu");

		String[] nomesNaoExatos = {"sam liu", "SAM LIU", "sAm LiU", "SAM liu"};
		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomesNaoExatos);
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesVistos());
		assertNull(diretoresRetornados.get(0).getQuantidadeDeFilmesPendentes());
	}
	
	private void preencherListaDeDiretores(String... nomes) {
		Arrays.stream(nomes).forEach(nome -> diretores.add(new Diretor(nome)));
	}
}
