package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContadorDeGeneroTest {

	ContadorDeGenero contador;
	
	@BeforeEach
	public void setUp() {
		contador = new ContadorDeGenero();
	}
	
	@Test
	public void deveRetornarDois() {
		List<Genero> generosMock = new ArrayList<>();
		String[] dados = {"Animação", "Animação", "Aventura"};
		
		for (String dado : dados) {
			generosMock.add(new Genero(dado));
		}
		
		List<Genero> generos = contador.definirOcorrencias(generosMock, dados);
		assertEquals(generos.get(0).getQuantidadeDeFilmes(), 2);
	}
	
	@Test
	public void deveRetornarNull() {
		List<Genero> generosMock = new ArrayList<>();
		String[] dados = {"Animação", "Animação", "Aventura"};

		generosMock.add(new Genero("Ação"));
		for (String dado : dados) {
			generosMock.add(new Genero(dado));
		}
		
		List<Genero> generos = contador.definirOcorrencias(generosMock, dados);
		assertNull(generos.get(0).getQuantidadeDeFilmes());
	}
	
	@Test
	public void deveRetornarMesmaListaQueFoiPassada() {
		List<Genero> generosMock = new ArrayList<>();
		String[] dados = {};

		List<Genero> generos = contador.definirOcorrencias(generosMock, dados);
		assertTrue(generos.containsAll(generosMock));
	}
}
