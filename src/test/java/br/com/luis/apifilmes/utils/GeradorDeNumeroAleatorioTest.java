package br.com.luis.apifilmes.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static br.com.luis.apifilmes.utils.GeradorDeNumeroAleatorio.*;

class GeradorDeNumeroAleatorioTest {

	@Test
	public void deveRetornarNumeroEntreZeroENove() {
		assertThat(gerar(10)).isBetween(0, 9);
	}

	@Test
	public void deveRetornarMenosUmQuandoNumeroForZero() {
		assertEquals(gerar(0), -1);
		assertEquals(gerar(-50), -1);
	}

}
