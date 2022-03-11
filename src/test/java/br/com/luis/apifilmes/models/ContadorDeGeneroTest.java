package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;

class ContadorDeGeneroTest {

	ContadorDeGenero contador;
	List<Genero> generos;
	final String[] dados = {"Animação", "Animação", "Aventura"};
	
	@BeforeEach
	void setUp() {
		contador = new ContadorDeGenero();
		generos = new ArrayList<>();
		preencherListaDeGeneros(dados);
	}
	
	@Test
	void deveRetornarValoresInteirosEPositivos() {
		List<Genero> generosRecebidos = contador.definirOcorrencias(generos, dados);
		assertEquals(generosRecebidos.get(0).getQuantidadeDeFilmes(), 2);
		assertEquals(generosRecebidos.get(2).getQuantidadeDeFilmes(), 1);
	}
	
	@Test
	void deveRetornarNullCasoQuantidadeDeFilmesSejaIgualAZero() {
		generos.add(new Genero("Ação"));
		
		List<Genero> generosRecebidos = contador.definirOcorrencias(generos, dados);
		assertEquals(generosRecebidos.get(0).getQuantidadeDeFilmes(), 2);
		assertEquals(generosRecebidos.get(2).getQuantidadeDeFilmes(), 1);
		assertNull(generos.get(3).getQuantidadeDeFilmes());
	}
	
	@Test
	void deveRetornarMesmaListaQueFoiPassada() {
		generos.clear();
		List<Genero> generosRecebidos = contador.definirOcorrencias(generos, dados);
		assertEquals(generos, generosRecebidos);
	}

	private void preencherListaDeGeneros(String... nomes) {
		Arrays.stream(nomes).forEach(genero -> generos.add(new Genero(genero)));
	}
}
