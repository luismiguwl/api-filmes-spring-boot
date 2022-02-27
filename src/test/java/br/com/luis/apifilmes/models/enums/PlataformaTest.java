package br.com.luis.apifilmes.models.enums;

import static org.junit.jupiter.api.Assertions.*;

import static br.com.luis.apifilmes.models.enums.Plataforma.*;

import org.junit.jupiter.api.Test;

public class PlataformaTest {
	
	@Test
	public void deveRetornarIndefinidoSeTextoNaoPossuirValor() {
		assertEquals(valueOfPersonalizado(" "), INDEFINIDO);
		assertEquals(valueOfPersonalizado(""), INDEFINIDO);
	}
	
	@Test
	public void deveRetornarTrueSeTextoForIgualAAlgumaPlataforma() {
		assertEquals(valueOfPersonalizado("Disney+"), DISNEY_PLUS);
		assertEquals(valueOfPersonalizado("Disney Plus"), DISNEY_PLUS);
	}
	
	@Test
	public void deveRetornarTrueSeTextoPossuirEspacos() {
		assertEquals(valueOfPersonalizado(" netflix     "), NETFLIX);
		assertEquals(valueOfPersonalizado("    NETFLIX    "), NETFLIX);
	}
	
	@Test
	public void deveLancarExcecaoCasoTextoNaoForIgualANenhumaPlataforma() {
		assertThrows(EnumConstantNotPresentException.class, () -> {
			valueOfPersonalizado("mock");
		});
	}
}
