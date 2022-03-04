package br.com.luis.apifilmes.models.utils;

import java.util.*;
import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.*;

import static br.com.luis.apifilmes.models.utils.DiretorUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiretorUtilsTest {

	List<Diretor> diretores;
	Diretor d1 = new Diretor("Christopher Nolan", 3);
	Diretor d2 = new Diretor("Vince Gilligan", 4);

	@BeforeEach
	void setUp() {
		diretores = new ArrayList<>();
		diretores.add(d1);
		diretores.add(d2);
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmes() {
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, 1), List.of(d2));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetido() {
		diretores.add(d2);
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, 1), List.of(d2));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetidoETopMaiorQueUm() {
		diretores.add(d2);
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, 2), List.of(d2, d1));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetidoETopMaiorQueOSizeDaLista() {
		diretores.add(d2);
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, 4), List.of(d2, d1));
	}
	
	@Test
	public void deveFiltrarDiretorComMaisFilmesComTopMenorQueUm() {
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, 0), List.of());
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComTopNegativo() {
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, -1), List.of());
	}

	@Test
	public void deveRetornarListaDeDiretoresOrdenadaPorQuantidadeDeFilmesDecrescenteComFilmeComQuantidadeDeFilmesVistosNula() {
		diretores.get(diretores.indexOf(d1)).setQuantidadeDeFilmesVistos(null);
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(diretores), List.of(d2));
	}

	@Test
	public void deveRetornarListaDeDiretoresOrdenadaPorQuantidadeDeFilmesDecrescenteComTodosOsFilmesComQuantidadeDeFilmesVistosNula() {
		diretores.get(diretores.indexOf(d1)).setQuantidadeDeFilmesVistos(null);
		diretores.get(diretores.indexOf(d2)).setQuantidadeDeFilmesVistos(null);
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(diretores), List.of());
	}

	@Test
	public void deveRetornarListaVaziaAoTentarOrdenarUmaListaVazia() {	
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(List.of()), List.of());
	}
}
