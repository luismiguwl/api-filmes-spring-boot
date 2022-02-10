package br.com.luis.apifilmes.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;

public class AnoAtualTest {

	AnoAtual ano;
	
	@BeforeEach
	public void setUp() {
		this.ano = new AnoAtual();
	}
	
	@Test
	public void deveRetornarAnoAtual() {
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		assertThat(anoAtual).isEqualTo(ano.get());
	}
}
