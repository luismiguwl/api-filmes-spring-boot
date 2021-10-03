package br.com.luis.apifilmes.utils;

import java.util.Random;

public class Calculadora {
	public int calcularPorcentagem(int valor, int total) {
		return (valor * 100) / total;
	}
	
	public int getNumeroAleatorio(int range) {
		if (range <= 0) {
			throw new IllegalArgumentException("Número precisa ser maior que 0!");
		}

		return new Random().nextInt(range + 1);
	}
}
