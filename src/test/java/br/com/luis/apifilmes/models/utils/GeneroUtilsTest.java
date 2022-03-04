package br.com.luis.apifilmes.models.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static br.com.luis.apifilmes.models.utils.GeneroUtils.*;

import java.util.*;
import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.*;

class GeneroUtilsTest {

	@Test
	void deveRetornarListaEmOrdemDecrescente() {
		Genero g1 = new Genero("Drama", 1);
		Genero g2 = new Genero("Aventura", 3);
		Genero g3 = new Genero("Crime", 2);
		
		assertThat(ordenarPorQuantidadeDecrescenteDeFilmes(List.of(g1, g2, g3))).containsExactlyInAnyOrder(g2, g3, g1);
	}

	@Test
	void deveRetornarListaNaMesmaOrdemQuandoQuantidadeDeFilmesDeTodosForemIguais() {
		Genero g1 = new Genero("Drama", 0);
		Genero g2 = new Genero("Aventura", 0);
		Genero g3 = new Genero("Crime", 0);
		
		assertThat(ordenarPorQuantidadeDecrescenteDeFilmes(List.of(g1, g2, g3))).containsExactlyInAnyOrder(g1, g2, g3);
	}

	@Test
	void deveRetornarListaNaMesmaOrdemQuandoApenasPrimeiroObjetoPossuirQuantidadeMaiorQueZero() {
		Genero g1 = new Genero("Drama", 1);
		Genero g2 = new Genero("Aventura", 0);
		Genero g3 = new Genero("Crime", 0);
		
		assertThat(ordenarPorQuantidadeDecrescenteDeFilmes(List.of(g1, g2, g3))).containsExactlyInAnyOrder(g1, g2, g3);
	}

	@Test
	void deveRetornarListaVazia() {
		assertThat(ordenarPorQuantidadeDecrescenteDeFilmes(List.of())).isEmpty();
	}

	@Test
	void deveLancarNullPointerQuandoListaForNula() {
		assertThrows(NullPointerException.class, () -> {
			ordenarPorQuantidadeDecrescenteDeFilmes(null);	
		});
	}
}
