package br.com.luis.apifilmes.models.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.*;

import static br.com.luis.apifilmes.models.utils.FilmeUtils.*;

public class FilmeUtilsTest {
	
	@Test
	public void deveBuscarFilmePorPalavra() {
		FilmeVisto filme = new FilmeVisto();
		filme.setTitulo("Batman Begins");
		filme.setDiretores(List.of(new Diretor("Chris")));
		List<FilmeVisto> filmes = List.of(filme);
		
		String chave = "Batman";
		
		List<FilmeVisto> listaRecebida = (List<FilmeVisto>) buscarFilmePorPalavra(filmes, chave);
		List<FilmeVisto> listaEsperada = List.of(filme);
		
		assertEquals(listaRecebida, listaEsperada);
	}
	
	@Test
	public void deveBuscarFilmePorPalavraComListaVazia() {
		String chave = "Batman";
		
		List<FilmeVisto> listaRecebida = (List<FilmeVisto>) buscarFilmePorPalavra(List.of(), chave);
		List<FilmeVisto> listaEsperada = List.of();
		
		assertEquals(listaRecebida, listaEsperada);
	}
	
	@Test
	public void deveBuscarFilmePorPalavraComChaveNula() {
		List<FilmeVisto> listaRecebida = (List<FilmeVisto>) buscarFilmePorPalavra(List.of(), null);
		List<FilmeVisto> listaEsperada = List.of();
		
		assertEquals(listaRecebida, listaEsperada);
	}
}
