package br.com.luis.apifilmes.arquivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import org.junit.jupiter.api.*;

class JoinerTest {
	
	Joiner joiner = new Joiner();
	
	@Test
	void deveRetornarStringSeparadaPorVirgula() {
		List<String> nomes = List.of("Vince Gilligan", "Michael Slovis");
		String retornoEsperado = "Vince Gilligan, Michael Slovis";
		String resultado = joiner.getDadosSeparadosPorVirgulaSeNecessario(nomes, String::toString);
		assertEquals(resultado, retornoEsperado);
	}
	
	@Test
	void deveRetornarValorQueFoiPassadoComoArgumento() {
		String retornoEsperado = "Breaking Bad";
		String resultado = joiner.getDadosSeparadosPorVirgulaSeNecessario(List.of(retornoEsperado), String::toString);
		assertEquals(resultado, retornoEsperado);
	}
}
