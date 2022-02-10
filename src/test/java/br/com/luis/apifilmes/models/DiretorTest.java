package br.com.luis.apifilmes.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class DiretorTest {

	Diretor diretor;
	
	@Test
	public void deveRetornarFilmesVistosNuloSeForIgualAZero() {
		diretor = new Diretor("Christopher Nolan");
		assertNull(diretor.getQuantidadeDeFilmesVistos());
	}
	
	@Test
	public void deveRetornarObjetoNaoNuloSeFilmesVistosForMaiorQueZero() {
		diretor = new Diretor("Christopher Nolan");
		diretor.setQuantidadeDeFilmesVistos(1);
		assertNotNull(diretor.getQuantidadeDeFilmesVistos());
	}
	
	@Test
	public void deveRetornarNomeNulo() {
		diretor = new Diretor();
		assertNull(diretor.getNome());
	}
	
	@Test
	public void deveRetornarNomeNulo2() {
		diretor = new Diretor();
		assertNull(diretor.getQuantidadeDeFilmesVistos());
	}
}
