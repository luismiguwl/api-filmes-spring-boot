package br.com.luis.apifilmes.utils;

import java.util.Random;

public class GeradorDeNumeroAleatorio {
	public static int gerar(int range) {
		if (range <= 0) {
			return -1;
		}

		return new Random().nextInt(range);
	}
}
