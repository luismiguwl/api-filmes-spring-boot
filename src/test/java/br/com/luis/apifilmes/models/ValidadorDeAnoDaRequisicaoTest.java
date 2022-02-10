package br.com.luis.apifilmes.models;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class ValidadorDeAnoDaRequisicaoTest {

	ValidadorDeAnoDaRequisicao validador;
	
	@Test
	public void deveRetornarTrueSeAnoForDoisMilEVinteEUm() {
		validador = new ValidadorDeAnoDaRequisicao(2021);
		assertTrue(validador.validar());
	}
	
	@Test
	public void deveRetornarTrueSeAnoForIgualAoAnoAtual() {
		int anoAtual = new AnoAtual().get();
		validador = new ValidadorDeAnoDaRequisicao(anoAtual);
		assertTrue(validador.validar());
	}
	
	@Test
	public void deveRetornarTrueSeAnoForOAnoAnteriorAoAtual() {
		int anoAtual = new AnoAtual().get();
		validador = new ValidadorDeAnoDaRequisicao(anoAtual - 1);
		assertTrue(validador.validar());
	}
	
	@Test
	public void deveRetornarFalseSeAnoForMenorQueDoisMilEVinteEUm() {
		validador = new ValidadorDeAnoDaRequisicao(2020);
		assertFalse(validador.validar());
	}
	
	@Test
	public void deveRetornarFalseSeAnoForMaiorQueOAnoAtual() {
		int anoAtual = new AnoAtual().get();
		validador = new ValidadorDeAnoDaRequisicao(anoAtual + 1);
		assertFalse(validador.validar());
	}
}
