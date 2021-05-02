package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.utils.Calculadora.getNumeroAleatorio;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {
	
	@Test
	@DisplayName("Deve calcular a porcentagem")
	public void deveCalcularPorcentagem() {
		int total = 100, valor = 50;
		assertThat(Calculadora.calcularPorcentagem(total, valor)).isEqualTo(50);
	}
	
	@Test
	@DisplayName("Deve retornar um valor entre 1 e 10")
	public void deveRetornarUmValorEntreUmEDez() {
		int valor = 11;
		assertThat(getNumeroAleatorio(valor)).isBetween(1, 10);
	}
	
	@Test
	@DisplayName("Deve retornar erro se número for negativo")
	public void deveRetornarErroSeNumeroForNegativo() {
		int valor = -1;
		assertThrows(IllegalArgumentException.class, () -> getNumeroAleatorio(valor));
	}
}