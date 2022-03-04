package br.com.luis.apifilmes.utils;

import java.util.Arrays;

public class VerificadorDeString {
	public boolean textoExisteNoArray(String textoAlvo, String... array) {
		return Arrays.stream(array).anyMatch(texto -> texto.equalsIgnoreCase(textoAlvo));
	}
}
