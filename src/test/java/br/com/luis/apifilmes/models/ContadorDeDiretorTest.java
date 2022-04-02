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
		assertEquals(diretoresRetornados.get(0).getTotalDeFilmesVistos(), 1);
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesPendentes());
	}

	@Test
	void deveRetornarDoisFilmesParaCadaDiretorQuandoHouverMaisDeUmDiretorNaListaEAmbosTiveremDoisFilmes() {
		String[] nomes = {"Sam Liu", "Sam Liu", "Christopher Nolan", "Christopher Nolan"};
		preencherListaDeDiretores(nomes);

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomes, "Christopher Nolan");
		assertEquals(diretoresRetornados.get(0).getTotalDeFilmesVistos(), 2);
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesPendentes());
		assertEquals(diretoresRetornados.get(2).getTotalDeFilmesVistos(), 2);
		assertEquals(diretoresRetornados.get(2).getTotalDeFilmesPendentes(), 1);
	}

	@Test
	void deveRetornarQuantidadeDeFilmesNula() {
		preencherListaDeDiretores("Sam Liu");

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, new String[]{"Fulano de tal"});
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesVistos());
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesPendentes());
	}

	@Test
	void deveRetornarQuantidadeDeFilmesNaoNulaEUmaNula() {
		preencherListaDeDiretores("Sam Liu");
		diretores.add(new Diretor("Fulano de Tal"));

		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, new String[]{"Sam Liu"}, new String[]{});
		assertEquals(diretoresRetornados.get(0).getTotalDeFilmesVistos(), 1);
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesPendentes());
		assertNull(diretoresRetornados.get(1).getTotalDeFilmesVistos());
		assertNull(diretoresRetornados.get(1).getTotalDeFilmesPendentes());
	}
	
	@Test
	void deveRetornarZeroFilmesVistosSeNomeNaoForExatamenteIgual() {
		preencherListaDeDiretores("Sam Liu");

		String[] nomesNaoExatos = {"sam liu", "SAM LIU", "sAm LiU", "SAM liu"};
		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomesNaoExatos);
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesVistos());
		assertNull(diretoresRetornados.get(0).getTotalDeFilmesPendentes());
	}
	
	private void preencherListaDeDiretores(String... nomes) {
		Arrays.stream(nomes).forEach(nome -> diretores.add(new Diretor(nome)));
	}
}
