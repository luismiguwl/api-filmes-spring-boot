package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class FormatadorDeDuracaoTest {

	Duracao duracao;
	FormatadorDeDuracao formatador = new FormatadorDeDuracao(duracao);

	@Test
	public void deveRetornarDoisMinutos() {
		formatador.setDuracao(new Duracao(2));
		assertEquals(formatador.get(), "2 minutos");
	}
	
	@Test
	public void deveRetornarHoraExata() {
		formatador.setDuracao(new Duracao(60));
		assertEquals(formatador.get(), "1h");
	}
	
	@Test
	public void deveRetornarHoraComMinutos() {
		formatador.setDuracao(new Duracao(61));
		assertEquals(formatador.get(), "1h 1m");
	}
	
	@Test
	public void deveRetornarCinquentaENoveMinutos() {
		formatador.setDuracao(new Duracao(59));
		assertEquals(formatador.get(), "59 minutos");
	}
	
	@Test
	public void deveRetornarZeroMinutos() {
		formatador.setDuracao(new Duracao(0));
		assertEquals(formatador.get(), "0 minutos");
	}
	
	@Test
	public void test() {
		formatador.setDuracao(new Duracao(1));
		assertNotEquals(formatador.get(), "0 minutos");
	}
	
}
