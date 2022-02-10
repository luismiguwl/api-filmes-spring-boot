package br.com.luis.apifilmes.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static br.com.luis.apifilmes.utils.GeradorDeNumeroAleatorio.*;

public class GeradorDeNumeroAleatorioTest {

	@Test
	public void deveRetornarNumeroEntreZeroENove() {
		int numero = gerar(10);
		assertThat(numero).isBetween(0, 9);
	}

	@Test
	public void deveLancarExcecaoSeNumeroForZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			gerar(0);
		});
	}

	@Test
	public void deveLancarExcecaoSeNumeroForNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			gerar(-1);
		});
	}

}
