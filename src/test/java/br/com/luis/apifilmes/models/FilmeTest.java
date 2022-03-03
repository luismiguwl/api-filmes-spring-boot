package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FilmeTest {

	Filme filme = new FilmeVisto();

	@Test
	public void deveRetornarTrueSeAnoEstiverNoRangeDefinido() {
		filme.setAnoDeLancamento(2020);
		assertTrue(filme.anoDeLancamentoEstaEntre(2019, 2021));
	}

	@Test
	public void deveRetornarTrueSeAnoEstiverNoRangeDefinidoComAnosIguais() {
		filme.setAnoDeLancamento(2020);
		assertTrue(filme.anoDeLancamentoEstaEntre(2020, 2020));
	}

	@Test
	public void deveRetornarTrueSeAnoEstiverNoRangeDefinidoComPrimeiroAnoMaiorQueOSegundo() {
		filme.setAnoDeLancamento(2020);
		assertTrue(filme.anoDeLancamentoEstaEntre(2022, 2019));
	}

	@Test
	public void deveRetornarTrueSeAnoEstiverNoRangeDefinidoComAmbosAnosMenoresQueAnoAlvo() {
		filme.setAnoDeLancamento(2020);
		assertFalse(filme.anoDeLancamentoEstaEntre(2015, 2016));
	}

	@Test
	public void deveRetornarFalseSeAnoNaoEstiverNoRangeDefinido() {
		filme.setAnoDeLancamento(2020);
		assertFalse(filme.anoDeLancamentoEstaEntre(2021, 2022));
	}
}