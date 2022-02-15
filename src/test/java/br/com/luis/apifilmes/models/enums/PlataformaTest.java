package br.com.luis.apifilmes.models.enums;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PlataformaTest {
	
	@Test
	public void deveRetornarOutrosSeTextoEstiverVazio() {
		Plataforma plataforma = Plataforma.valueOfPersonalizado("");
		assertEquals(Plataforma.OUTROS, plataforma);
	}
	
	@Test
	public void deveRetornarOutrosSeTextoEstiverEmBranco() {
		Plataforma plataforma = Plataforma.valueOfPersonalizado(" ".repeat(2));
		assertEquals(Plataforma.OUTROS, plataforma);
	}
	
	@Test
	public void deveRetornarTrueSeTextoForIgualAAlgumaPlataforma() {
		String texto = Plataforma.DISNEY_PLUS.getNome();
		assertEquals(Plataforma.valueOfPersonalizado(texto), Plataforma.DISNEY_PLUS);
	}
	
	@Test
	public void deveLancarExcecaoCasoTextoNaoForIgualANenhumaPlataforma() {
		String texto = "mock".repeat(5);
		assertThrows(IllegalArgumentException.class, () -> {
			Plataforma.valueOfPersonalizado(texto);
		});
	}
}
