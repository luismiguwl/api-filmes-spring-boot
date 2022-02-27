package br.com.luis.apifilmes.models.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlataformaTest {
	
	@Test
	public void deveRetornarIndefinidoSeTextoNaoPossuirValor() {
		Plataforma plataforma = Plataforma.valueOfPersonalizado("");
		assertEquals(Plataforma.INDEFINIDO, plataforma);
	}
	
	@Test
	public void deveRetornarTrueSeTextoForIgualAAlgumaPlataforma() {
		String texto = Plataforma.DISNEY_PLUS.getNomes()[0];
		assertEquals(Plataforma.valueOfPersonalizado(texto), Plataforma.DISNEY_PLUS);
	}
	
	@Test
	public void deveLancarExcecaoCasoTextoNaoForIgualANenhumaPlataforma() {
		String texto = "mock".repeat(5);
		assertThrows(EnumConstantNotPresentException.class, () -> {
			Plataforma.valueOfPersonalizado(texto);
		});
	}
}
