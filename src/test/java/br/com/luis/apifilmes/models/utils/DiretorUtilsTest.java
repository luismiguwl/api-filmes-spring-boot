package br.com.luis.apifilmes.models.utils;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.Diretor;

import static br.com.luis.apifilmes.models.utils.DiretorUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiretorUtilsTest {

	@Test
	public void deveFiltrarDiretorComMaisFilmes() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);
		
		int top = 1;
		
		List<Diretor> diretores = List.of(d1, d2);
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of(d2));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetido() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);

		Diretor d3 = new Diretor("Vince Gilligan");
		d3.setQuantidadeDeFilmesVistos(4);
		
		int top = 1;
		
		List<Diretor> diretores = List.of(d1, d2, d3);
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of(d2));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetidoETopMaiorQueUm() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);

		Diretor d3 = new Diretor("Vince Gilligan");
		d3.setQuantidadeDeFilmesVistos(4);
		
		int top = 2;
		
		List<Diretor> diretores = List.of(d1, d2, d3);
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of(d2, d1));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetidoETopIgualAoSizeDaLista() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);

		Diretor d3 = new Diretor("Vince Gilligan");
		d3.setQuantidadeDeFilmesVistos(4);
		
		List<Diretor> diretores = List.of(d1, d2, d3);
		int top = diretores.size();
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of(d2, d1));
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComDiretorRepetidoETopMaiorQueOSizeDaLista() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);

		Diretor d3 = new Diretor("Vince Gilligan");
		d3.setQuantidadeDeFilmesVistos(4);
		
		int top = 4;
		
		List<Diretor> diretores = List.of(d1, d2, d3);
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of(d2, d1));
	}
	
	@Test
	public void deveFiltrarDiretorComMaisFilmesComTopMaiorQueUm() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);
		
		List<Diretor> diretores = List.of(d1, d2);
		int top = diretores.size();

		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of(d2, d1));
	}
	
	@Test
	public void deveFiltrarDiretorComMaisFilmesComTopMenorQueUm() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);
		
		int top = 0;
		
		List<Diretor> diretores = List.of(d1, d2);
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of());
	}

	@Test
	public void deveFiltrarDiretorComMaisFilmesComTopNegativo() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);
		
		int top = -1;
		
		List<Diretor> diretores = List.of(d1, d2);
		
		assertEquals(filtrarDiretoresComMaisFilmes(diretores, top), List.of());
	}
	
	@Test
	public void deveRetornarListaDeDiretoresOrdenadaPorQuantidadeDeFilmesDecrescente() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(3);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);
		
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(List.of(d1, d2)), List.of(d2, d1));
	}

	@Test
	public void deveRetornarListaDeDiretoresOrdenadaPorQuantidadeDeFilmesDecrescenteComFilmeComQuantidadeDeFilmesVistosNula() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(null);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(4);
		
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(List.of(d1, d2)), List.of(d2));
	}

	@Test
	public void deveRetornarListaDeDiretoresOrdenadaPorQuantidadeDeFilmesDecrescenteComTodosOsFilmesComQuantidadeDeFilmesVistosNula() {
		Diretor d1 = new Diretor("Christopher Nolan");
		d1.setQuantidadeDeFilmesVistos(null);
		
		Diretor d2 = new Diretor("Vince Gilligan");
		d2.setQuantidadeDeFilmesVistos(null);
		
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(List.of(d1, d2)), List.of());
	}

	@Test
	public void deveRetornarListaVaziaAoTentarOrdenarUmaListaVazia() {	
		assertEquals(ordenarDecrescentePorQuantidadeDeFilmes(List.of()), List.of());
	}
}
