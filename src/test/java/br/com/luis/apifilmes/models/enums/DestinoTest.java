package br.com.luis.apifilmes.models.enums;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.AnoAtual;

public class DestinoTest {

	@Test
	public void deveRetornarDestinoBaseadoNoAno() {
		Destino destinoEsperado = Destino.VISTOS_EM_2021;
		assertEquals(destinoEsperado, Destino.obterDestinoBaseadoNoAno(2021));
	}
	
	@Test
	public void deveLancarExcecaoCasoEnumNaoExista() {
		assertThrows(EnumConstantNotPresentException.class, () -> {
			Destino.obterDestinoBaseadoNoAno(2020);
		});
	}
	
	@Test
	public void deveRetornarTrueSeEnumExistir() {
		assertTrue(Destino.enumExiste(Destino.PENDENTES.name()));
	}

	@Test
	public void deveRetornarDestinoParaFilmesVistosNoAnoAtual() {
		int anoAtual = new AnoAtual().get();
		assertEquals(Destino.obterDestinoBaseadoNoAno(anoAtual), Destino.obterDestinoBaseadoNoAnoAtual());
	}
	
	@Test
	public void deveRetornarTrueSeDestinoForPendente() {
		assertTrue(Destino.PENDENTES.ehFilmePendente());
	}
	
	@Test
	public void deveRetornarFalseSeDestinoNaoForPendente() {
		assertFalse(Destino.VISTOS_EM_2022.ehFilmePendente());
	}
}
