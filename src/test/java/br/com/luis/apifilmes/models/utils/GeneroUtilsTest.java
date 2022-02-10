package br.com.luis.apifilmes.models.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.Genero;

public class GeneroUtilsTest {

	@Test
	public void deveRetornarListaDeGenerosEmOrdemCrescente() {
		Genero g1 = new Genero("Drama");
		g1.setQuantidadeDeFilmes(1);
		
		Genero g2 = new Genero("Aventura");
		g2.setQuantidadeDeFilmes(3);
		
		Genero g3 = new Genero("Crime");
		g3.setQuantidadeDeFilmes(2);
		
		List<Genero> generos = List.of(g1, g2, g3);
		List<Genero> listaRecebida = GeneroUtils.getListaDeGenerosOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(generos);
		List<Genero> listaEsperada = List.of(g2, g3, g1);
		
		assertEquals(listaRecebida, listaEsperada);
	}
}
