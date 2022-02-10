package br.com.luis.apifilmes.models;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class GeneroTest {

	Genero genero;
	
	@Test
	public void deveRetornarNuloSeQuantidadeDeFilmeForZero() {
		genero = new Genero("Drama");
		assertNull(genero.getQuantidadeDeFilmes());
	}
	
	@Test
	public void deveRetornarUmSeQuantidadeDeFilmeForIgualAUm() {
		genero = new Genero("Drama");
		genero.setQuantidadeDeFilmes(1);
		assertNotNull(genero.getQuantidadeDeFilmes());
	}
	
	@Test
	public void deveRetornarNomeNull() {
		genero = new Genero();
		assertNull(genero.getNome());
	}
}
