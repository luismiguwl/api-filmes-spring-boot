package br.com.luis.apifilmes.models.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.utils.VerificadorDeString;

public class VerificadorDeStringTest {
	String[] array = {"Days Before Rodeo", "BITTSM"};
	String alvo = "JACKBOYS";
	
	VerificadorDeString verificador;
	
	@BeforeEach
	public void setUp() {
		verificador = new VerificadorDeString();
	}
	
	@Test
	public void deveRetornarFalseSeNaoEncontrarPalavraNoArray() {
		assertFalse(verificador.textoExisteNoArray(alvo, array));
	}
	
	@Test
	public void deveRetornarTrueSeEncontrarPalavraNoArray() {
		alvo = array[0];
		assertTrue(verificador.textoExisteNoArray(alvo, array));
	}
}
