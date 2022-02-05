package br.com.luis.apifilmes.utils;

import java.util.Random;

public class GeradorDeNumeroAleatorio {
	public static int gerar(int range) {
		if (range <= 0) {
			throw new IllegalArgumentException("Número precisa ser maior que 0!");
		}

		return new Random().nextInt(range);
	}
}
