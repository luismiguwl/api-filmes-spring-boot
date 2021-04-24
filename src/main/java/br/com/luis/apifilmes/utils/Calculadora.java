package br.com.luis.apifilmes.utils;

import java.util.Random;

public class Calculadora {
	public static int calcularPorcentagem(int total, int valor) {
		return (valor * 100) / total;
	}
	
	public static int getNumeroAleatorio(int range) {
		int randomNumber = new Random().nextInt(range);
		return randomNumber;
	}
}
