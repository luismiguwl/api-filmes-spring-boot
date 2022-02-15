package br.com.luis.apifilmes.arquivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class JoinerTest {
	
	Joiner joiner = new Joiner();
	
	@Test
	public void deveRetornarStringSeparadaPorVirgula() {
		List<String> nomes = List.of("Vince Gilligan", "Michael Slovis");
		String retornoEsperado = String.join(", ", nomes.get(0), nomes.get(1));
		String resultado = joiner.getDadosSeparadosPorVirgulaSeNecessario(nomes, String::toString);
		assertEquals(resultado, retornoEsperado);
	}
	
	@Test
	public void deveRetornarValorQueFoiPassadoComoArgumento() {
		String retornoEsperado = "Breaking Bad";
		String resultado = joiner.getDadosSeparadosPorVirgulaSeNecessario(List.of(retornoEsperado), String::toString);
		assertEquals(resultado, retornoEsperado);
	}
}
