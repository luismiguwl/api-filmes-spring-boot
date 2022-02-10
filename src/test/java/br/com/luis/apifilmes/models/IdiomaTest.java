package br.com.luis.apifilmes.models;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class IdiomaTest {

	Idioma idioma;
	
	@Test
	public void deveRetornarObjetoNaoNuloQuandoForPassadoNomeNoConstrutor() {
		idioma = new Idioma("Inglês");
		assertNotNull(idioma.getNome());
	}
	
	@Test
	public void deveRetornarObjetoNuloQuandoNaoForPassadoNomeNoConstrutor() {
		idioma = new Idioma();
		assertNull(idioma.getNome());
	}
}
