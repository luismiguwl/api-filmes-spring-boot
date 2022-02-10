package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DuracaoTest {
	Duracao duracao;
	
	@Test
	public void deveRetornarUmaHoraCompleta() {
		duracao = new Duracao(60);
		assertEquals(duracao.getHoras(), 1);
		assertEquals(duracao.getMinutos(), 0);
	}
	
	@Test
	public void deveRetornarDuasHorasCompletas() {
		duracao = new Duracao(120);
		assertEquals(duracao.getHoras(), 2);
		assertEquals(duracao.getMinutos(), 0);
	}
	
	@Test
	public void deveRetornarUmaHoraEUmMinuto() {
		duracao = new Duracao(61);
		assertEquals(duracao.getHoras(), 1);
		assertEquals(duracao.getMinutos(), 1);
	}
	
	@Test
	public void deveRetornarCinquentaENoveMinutosEZeroHoras() {
		duracao = new Duracao(59);
		assertEquals(duracao.getHoras(), 0);
		assertEquals(duracao.getMinutos(), 59);
	}
	
	@Test
	public void deveRetornarZeroHorasEZeroMinutos() {
		duracao = new Duracao(0);
		assertEquals(duracao.getHoras(), 0);
		assertEquals(duracao.getMinutos(), 0);
	}
	
	@Test
	public void deveLancarExcecaoCasoDuracaoSejaUmNumeroNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			duracao = new Duracao(-1);
		});
	}

	@Test
	public void deveRetornarCinquentaMinutos() {
		duracao = new Duracao(50);
		assertEquals(duracao.getDuracaoEmMinutos(), 50);	
	}

	@Test
	public void deveRetornarUmaHoraExata() {
		duracao = new Duracao(60);
		assertEquals(duracao.getDuracaoFormatada(), "1h");	
	}
}
