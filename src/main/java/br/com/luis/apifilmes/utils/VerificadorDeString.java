package br.com.luis.apifilmes.utils;

public class VerificadorDeString {
	public boolean textoExisteNoArray(String texto, String... array) {
		for (String elemento : array) {
			if (elemento.equalsIgnoreCase(texto)) {
				return true;
			}
		}
		
		return false;
	}
}
