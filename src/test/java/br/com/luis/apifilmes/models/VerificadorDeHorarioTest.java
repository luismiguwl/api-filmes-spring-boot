package br.com.luis.apifilmes.models;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class VerificadorDeHorarioTest {
	
	Duracao duracao = new Duracao(0);
	VerificadorDeHorario verificador = new VerificadorDeHorario(duracao);

	@Test
	public void deveRetornarTrueSeDuracaoPossuirApenasUmMinuto() {
		verificador.setDuracao(new Duracao(1));
		assertTrue(verificador.possuiApenasUmMinuto());
	}

	@Test
	public void deveRetornarFalseSeDuracaoNaoPossuirApenasUmMinuto() {
		verificador.setDuracao(new Duracao(2));
		assertFalse(verificador.possuiApenasUmMinuto());
	}
	
	@Test
	public void deveRetornarFalseSeDuracaoNaoPossuirApenasUmMinuto2() {
		verificador.setDuracao(new Duracao(61));
		assertFalse(verificador.possuiApenasUmMinuto());
	}
	
	@Test
	public void deveRetornarTrueSePossuirMenosDeUmaHora() {
		verificador.setDuracao(new Duracao(59));
		assertTrue(verificador.possuiMenosDeUmaHora());
	}
	
	@Test
	public void deveRetornarFalseSePossuirUmaHora() {
		verificador.setDuracao(new Duracao(60));
		assertFalse(verificador.possuiMenosDeUmaHora());
	}
	
	@Test
	public void deveRetornarFalseSePossuirUmaHora2() {
		verificador.setDuracao(new Duracao(60));
		assertFalse(verificador.possuiMenosDeUmaHora());
	}
	
	@Test
	public void deveRetornarTrueSePossuirHoraExata() {
		verificador.setDuracao(new Duracao(60));
		assertTrue(verificador.possuiHoraExata());
	}
	
	@Test
	public void deveRetornarFalseSeNaoPossuirHoraExata() {
		verificador.setDuracao(new Duracao(59));
		assertFalse(verificador.possuiHoraExata());
	}
	
	@Test
	public void deveRetornarFalseSeNaoPossuirHoraExata2() {
		verificador.setDuracao(new Duracao(59));
		assertFalse(verificador.possuiHoraExata());
	}
	
	@Test
	public void deveRetornarTrueSePossuirZeroHorasEZeroMinutos() {
		verificador.setDuracao(new Duracao(0));
		assertTrue(verificador.possuiZeroHorasEMinutos());
	}
	
	@Test
	public void deveRetornarFalseSePossuirNaoZeroHorasEZeroMinutos() {
		verificador.setDuracao(new Duracao(1));
		assertFalse(verificador.possuiZeroHorasEMinutos());
	}
}
