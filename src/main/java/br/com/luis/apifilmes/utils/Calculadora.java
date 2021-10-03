package br.com.luis.apifilmes.utils;

import java.util.Random;

public class Calculadora {
	public static int calcularPorcentagem(int valor, int total) {
		return (valor * 100) / total;
	}
	
	public static int getNumeroAleatorio(int range) {
		if (range < 0 || range == 0) {
			throw new IllegalArgumentException("Número precisa ser positivo!");
		}
		int randomNumber = new Random().nextInt(range + 1);
		return randomNumber;
	}
}
