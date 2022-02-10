package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContadorDeDiretorTest {
	
	ContadorDeDiretor contador;
	
	@BeforeEach
	public void setUp() {
		contador = new ContadorDeDiretor();
	}
	
	@Test
	public void deveRetornarDoisFilmesVistos() {
		List<Diretor> diretores = new ArrayList<>();
		String[] nomes = {"Christopher Nolan", "Sam Liu", "Sam Liu"};
		
		for (String nome : nomes) {
			diretores.add(new Diretor(nome));
		}
		
		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomes);
		Diretor diretor = diretoresRetornados.get(1);
		assertEquals(diretor.getQuantidadeDeFilmesVistos(), 2);
	}
	
	@Test
	public void deveRetornarNulo() {
		List<Diretor> diretores = new ArrayList<>();
		String[] nomes = {"Christopher Nolan", "Sam Liu", "Sam Liu"};
		
		diretores.add(new Diretor("Vince Gilligan"));
		for (String nome : nomes) {
			diretores.add(new Diretor(nome));
		}
		
		List<Diretor> diretoresRetornados = contador.definirOcorrencias(diretores, nomes);
		Diretor diretor = diretoresRetornados.get(0);
		assertNull(diretor.getQuantidadeDeFilmesVistos());
	}
}
