package br.com.luis.apifilmes.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {
	
	private Calculadora calculadora = Calculadora.get();
	
	@Test
	@DisplayName("Deve calcular a porcentagem corretamente")
	public void deveCalcularPorcentagem() {
		int total = 100, valor = 50;
		assertThat(calculadora.calcularPorcentagem(valor, total)).isEqualTo(50);
	}

	@Test
	@DisplayName("Deve retornar um ArithmeticException se número total for negativo")
	public void deveRetornarExcecaoAoTentarCalcularPorcentagemSeNumeroTotalForIgualAZero() {
		int valor = 10, total = 0;
		assertThrows(ArithmeticException.class, () -> calculadora.calcularPorcentagem(valor, total));
	}
	
	@Test
	@DisplayName("Deve retornar um valor aleatório entre 0 e 10")
	public void deveRetornarUmValorEntreUmEDez() {
		int valor = 10;
		assertThat(calculadora.getNumeroAleatorio(valor)).isBetween(0, 9);
	}

	@Test
	@DisplayName("Deve retornar um IllegalArgumentException ao tentar gerar um número aleatório com range 0")
	public void deveRetornarExcecaoAoTentarGerarNumeroAleatorioComRangeZero() {
		assertThrows(IllegalArgumentException.class, () -> calculadora.getNumeroAleatorio(0));
	}
}