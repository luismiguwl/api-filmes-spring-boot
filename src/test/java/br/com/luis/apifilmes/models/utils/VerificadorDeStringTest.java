package br.com.luis.apifilmes.models.utils;

import static org.junit.Assert.*;

import org.assertj.core.api.*;
import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.utils.*;

class VerificadorDeStringTest {
	String[] palavras = {"Days Before Rodeo", "BITTSM"};
	VerificadorDeString verificador = new VerificadorDeString();
	
	@Test
	void deveRetornarTrueSeEncontrarPalavraNoArray() {
		assertTrue(verificador.textoExisteNoArray(palavras[0], palavras));
	}

	@Test
	void deveRetornarTrueSeEncontrarPalavraNoArrayIndependenteDaCaixa() {
		assertTrue(verificador.textoExisteNoArray(palavras[0].toLowerCase(), palavras));
		assertTrue(verificador.textoExisteNoArray(palavras[0].toUpperCase(), palavras));
		assertTrue(verificador.textoExisteNoArray("bItTsM", palavras));
	}

	@Test
	void deveRetornarFalseSeNaoEncontrarPalavraNoArray() {
		assertFalse(verificador.textoExisteNoArray("JACKBOYS", palavras));
	}

	@Test
	void deveRetornarFalseSePalavraForNula() {
		assertFalse(verificador.textoExisteNoArray(null, palavras));
	}

	@Test
	void deveRetornarFalseSePalavraExistirNoArrayPoremContemEspacos() {
		String palavraComEspaco = String.format("  %s  ", palavras[0]);
		assertFalse(verificador.textoExisteNoArray(palavraComEspaco, palavras));
	}

	@Test
	void deveRetornarFalseSeArrayEstiverVazio() {
		assertFalse(verificador.textoExisteNoArray(palavras[0], new String[]{}));
	}

}
