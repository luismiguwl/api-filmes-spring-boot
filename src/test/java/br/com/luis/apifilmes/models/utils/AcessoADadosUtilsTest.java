package br.com.luis.apifilmes.models.utils;

import static org.junit.jupiter.api.Assertions.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.Diretor;

public class AcessoADadosUtilsTest {
	
	@Test
	public void deveConverterStringsVaziaParaListaDeObjeto() {
		String[] nomes = {};
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nomes);
		assertTrue(diretores.isEmpty());
	}
	
	@Test
	public void deveConverterStringsParaListaDeObjeto() {
		String[] nomes = {"Phil Lord", "Christopher Miller"};
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nomes);
		
		assertEquals(diretores.get(0).getNome(), nomes[0]);
		assertEquals(diretores.get(1).getNome(), nomes[1]);
	}
	
	@Test
	public void deveConverterStringsContendoVirgulaParaListaDeObjeto() {
		String[] nomes = {"Phil Lord", "Christopher Miller", "Michael Slovis, Vince Gilligan"};
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nomes);
		
		assertEquals(diretores.size(), 4);
		assertEquals(diretores.get(0).getNome(), "Phil Lord");
		assertEquals(diretores.get(1).getNome(), "Christopher Miller");
		assertEquals(diretores.get(2).getNome(), "Michael Slovis");
		assertEquals(diretores.get(3).getNome(), "Vince Gilligan");
	}
	
	@Test
	public void deveConverterStringParaObjeto() {
		String nome = "Michael Slovis";
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nome);
		assertEquals(diretores.get(0).getNome(), nome);
	}
	
	@Test
	public void deveConverterStringParaObjetoComDadosNaoIntegros() {
		String nome = "Michael Slovis,,,,,";
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nome);
		
		assertEquals(diretores.size(), 1);
		assertEquals(diretores.get(0).getNome(), "Michael Slovis");
	}
	
	@Test
	public void deveObterArrayContendoAtributosDeUmaClasseComListaVazia() {
		List<Diretor> diretores = List.of();
		String[] arrayRecebido = obterArrayContendoDadosDoAtributoDeUmaClasse(Diretor::getNome, diretores);
		assertEquals(arrayRecebido.length, 0);
	}
	
	@Test
	public void deveObterArrayContendoAtributosDeUmaClasse() {
		List<Diretor> diretores = List.of(
				new Diretor("Christopher Nolan"),
				new Diretor("Vince Gilligan"));
		
		String[] arrayEsperado = {diretores.get(0).getNome(), diretores.get(1).getNome()};
		String[] arrayRecebido = obterArrayContendoDadosDoAtributoDeUmaClasse(Diretor::getNome, diretores);
		
		assertArrayEquals(arrayRecebido, arrayEsperado);
	}
	
	@Test
	public void deveConverterListaDeStringParaArray() {
		List<String> nomes = List.of("Time Flies", "Landed");

		String[] arrayEsperado = {nomes.get(0), nomes.get(1)};
		String[] arrayRecebido = converterListaDeStringParaArray(nomes);
		
		assertArrayEquals(arrayRecebido, arrayEsperado);
	}
	
	@Test
	public void deveConverterListaDeStringParaArrayRetornandoArrayVazio() {
		List<String> nomes = new ArrayList<>();
		String[] arrayRecebido = converterListaDeStringParaArray(nomes);
		
		assertEquals(arrayRecebido.length, 0);
	}
}
