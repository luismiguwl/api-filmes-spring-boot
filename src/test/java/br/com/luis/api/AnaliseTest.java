package br.com.luis.api;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import br.com.luis.apifilmes.utils.Analise;

public class AnaliseTest {
	private Analise analise = new Analise();;

	@BeforeEach
	public void setUp() {
		analise = new Analise();
	}
	
	@Test
	public void deveRetornarErroAoTentarDividirNumeroPorZero() {
		int minutos = 0;
		analise.definirQuantidadeDeDias(minutos);
	}
}
