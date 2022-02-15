package br.com.luis.apifilmes.utils;

import java.util.Arrays;

public class VerificadorDeString {
	public boolean textoExisteNoArray(String texto, String... array) {
		return Arrays.asList(array).contains(texto);
	}
}
