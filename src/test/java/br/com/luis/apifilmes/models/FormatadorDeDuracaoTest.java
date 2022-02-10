package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FormatadorDeDuracaoTest {

	FormatadorDeDuracao formatador;
	Duracao duracao;

	@Test
	public void deveRetornarUmMinuto() {
		duracao = new Duracao(1);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertEquals(formatador.get(), "1 minuto");
	}
	
	@Test
	public void deveRetornarValorDiferenteDeUmMinuto() {
		duracao = new Duracao(2);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertNotEquals(formatador.get(), "1 minuto");
	}
	
	@Test
	public void deveRetornarDoisMinutos() {
		duracao = new Duracao(2);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertEquals(formatador.get(), "2 minutos");
	}
	
	@Test
	public void deveRetornarValorDiferenteDeDoisMinutos() {
		duracao = new Duracao(60);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertNotEquals(formatador.get(), "2 minutos");
	}
	
	@Test
	public void deveRetornarHoraExata() {
		duracao = new Duracao(60);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertEquals(formatador.get(), "1h");
	}
	
	@Test
	public void deveRetornarValorDiferenteDeHoraExata() {
		duracao = new Duracao(61);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertNotEquals(formatador.get(), "1h");
	}
	
	@Test
	public void deveRetornarZeroMinutos() {
		duracao = new Duracao(0);
		formatador = new FormatadorDeDuracao(duracao);
		
		assertNotEquals(formatador.get(), "0 minutos");
	}
	
}
