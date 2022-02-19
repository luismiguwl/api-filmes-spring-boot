package br.com.luis.apifilmes.models.enums;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
