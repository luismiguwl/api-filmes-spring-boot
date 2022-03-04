package br.com.luis.apifilmes.models.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.*;

import static br.com.luis.apifilmes.models.utils.FilmeUtils.*;

class FilmeUtilsTest {
	
	Filme filme = new FilmeVisto();
	String titulo = "Batman Begins";
	String chave = "Batman";

	@BeforeEach
	void setUp() {
		filme.setTitulo(titulo);
	}

	@Test
	void deveEncontrarFilme() {
		assertEquals(buscarFilmePorPalavra(List.of(filme), chave), List.of(filme));
	}

	@Test
	void deveEncontrarFilmeIndependenteDaCaixaDaChave() {
		assertEquals(buscarFilmePorPalavra(List.of(filme), chave.toLowerCase()), List.of(filme));
		assertEquals(buscarFilmePorPalavra(List.of(filme), chave.toUpperCase()), List.of(filme));
	}
	
	@Test
	void deveRetornarListaVazia() {
		assertEquals(buscarFilmePorPalavra(List.of(), chave), List.of());
	}

	@Test
	void deveRetornarListaVaziaSeChaveConterEspacos() {
		assertEquals(buscarFilmePorPalavra(List.of(filme), chave + " "), List.of(filme));
	}

	@Test
	void deveRetornarListaVaziaSeChaveForVazia() {
		assertEquals(buscarFilmePorPalavra(List.of(filme), ""), List.of());
	}

	@Test
	void deveRetornarListaVaziaSeDadosDoFilmeNaoConterChave() {
		assertEquals(buscarFilmePorPalavra(List.of(filme), "Vital"), List.of());
	}

}
