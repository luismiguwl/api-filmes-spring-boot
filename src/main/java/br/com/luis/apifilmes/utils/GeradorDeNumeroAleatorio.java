package br.com.luis.apifilmes.utils;

import java.util.Random;

public class GeradorDeNumeroAleatorio {
	public static int gerar(int range) {
		return range > 0 ? new Random().nextInt(range) : -1;
	}
}
