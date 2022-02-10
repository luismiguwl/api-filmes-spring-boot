package br.com.luis.apifilmes.models;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class VerificadorDeHorarioTest {
	
	Duracao duracao;
	VerificadorDeHorario verificador;

	@Test
	public void deveRetornarTrueSeDuracaoPossuirApenasUmMinuto() {
		duracao = new Duracao(1);
		verificador = new VerificadorDeHorario(duracao);
		
		assertTrue(verificador.possuiApenasUmMinuto());
	}

	@Test
	public void deveRetornarFalseSeDuracaoNaoPossuirApenasUmMinuto() {
		duracao = new Duracao(60);
		verificador = new VerificadorDeHorario(duracao);
		
		assertFalse(verificador.possuiApenasUmMinuto());
	}
	
	@Test
	public void deveRetornarFalseSeDuracaoNaoPossuirApenasUmMinuto2() {
		duracao = new Duracao(61);
		verificador = new VerificadorDeHorario(duracao);
		
		assertFalse(verificador.possuiApenasUmMinuto());
	}
	
	@Test
	public void deveRetornarTrueSePossuirMenosDeUmaHora() {
		duracao = new Duracao(59);
		verificador = new VerificadorDeHorario(duracao);
		
		assertTrue(verificador.possuiMenosDeUmaHora());
	}
	
	@Test
	public void deveRetornarFalseSePossuirUmaHora() {
		duracao = new Duracao(60);
		verificador = new VerificadorDeHorario(duracao);
		
		assertFalse(verificador.possuiMenosDeUmaHora());
	}
	
	@Test
	public void deveRetornarFalseSePossuirUmaHora2() {
		duracao = new Duracao(61);
		verificador = new VerificadorDeHorario(duracao);
		
		assertFalse(verificador.possuiMenosDeUmaHora());
	}
	
	@Test
	public void deveRetornarTrueSePossuirHoraExata() {
		duracao = new Duracao(60);
		verificador = new VerificadorDeHorario(duracao);
		
		assertTrue(verificador.possuiHoraExata());
	}
	
	@Test
	public void deveRetornarFalseSeNaoPossuirHoraExata() {
		duracao = new Duracao(59);
		verificador = new VerificadorDeHorario(duracao);
		
		assertFalse(verificador.possuiHoraExata());
	}
	
	@Test
	public void deveRetornarFalseSeNaoPossuirHoraExata2() {
		duracao = new Duracao(61);
		verificador = new VerificadorDeHorario(duracao);
		
		assertFalse(verificador.possuiHoraExata());
	}
}
