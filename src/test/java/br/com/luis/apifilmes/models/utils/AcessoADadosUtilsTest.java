package br.com.luis.apifilmes.models.utils;

import static org.junit.jupiter.api.Assertions.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

import java.util.*;
import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.*;

class AcessoADadosUtilsTest {
	
	@Test
	void deveRetornarListaVaziaAoTentarConverterUmArrayVazio() {
		assertTrue(converterStringParaObjeto(Diretor::new, new String[]{}).isEmpty());
	}

	@Test
	void deveRetornarListaVaziaAoTentarConverterUmArrayComUmaStringVazia() {
		assertTrue(converterStringParaObjeto(Diretor::new, "").isEmpty());
	}

	@Test
	void deveRetornarListaVaziaQuandoHouverApenasUmaStringQueContemVirgula() {
		assertTrue(converterStringParaObjeto(Diretor::new, ",").isEmpty());
	}

	@Test
	void deveRetornarListaVaziaQuandoHouverVariasStringQuePossuemApenasVirgula() {
		assertTrue(converterStringParaObjeto(Diretor::new, ",,,,,,,,").isEmpty());
	}
	
	@Test
	void deveRetornarListaDeDiretoresContendoNomesDaLista() {
		String[] nomes = {"Phil Lord", "Christopher Miller"};
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nomes);
		
		assertEquals(diretores.get(0).getNome(), nomes[0]);
		assertEquals(diretores.get(1).getNome(), nomes[1]);
	}

	@Test
	void deveRetornarListaDeDiretoresContendoNomesDaListaQuandoHouverApenasUmaStringContendoVirgula() {
		String[] nomes = {"Phil Lord, Christopher Miller"};
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nomes);
		
		assertEquals(diretores.get(0).getNome(), "Phil Lord");
		assertEquals(diretores.get(1).getNome(), "Christopher Miller");
	}
	
	@Test
	void deveRetornarListaComDiretoresQuandoHouverAlgumaStringContendoVirgula() {
		String[] nomes = {"Phil Lord", "Christopher Miller", "Michael Slovis, Vince Gilligan"};
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, nomes);

		assertEquals(diretores.size(), 4);
		assertEquals(diretores.get(0).getNome(), nomes[0]);
		assertEquals(diretores.get(1).getNome(), nomes[1]);
		assertEquals(diretores.get(2).getNome(), "Michael Slovis");
		assertEquals(diretores.get(3).getNome(), "Vince Gilligan");
	}
	
	@Test
	void deveConverterStringParaObjeto() {
		assertEquals(converterStringParaObjeto(Diretor::new, "Michael Slovis").get(0).getNome(), "Michael Slovis");
	}
	
	@Test
	void deveConverterStringParaObjetoComDadosNaoIntegros() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, "Michael Slovis,,,,,");
		
		assertEquals(diretores.size(), 1);
		assertEquals(diretores.get(0).getNome(), "Michael Slovis");
	}

	@Test
	void deveConverterStringParaObjetoComDadosNaoIntegrosContendoVirgulaNoComeco() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, ",,,,,,Michael Slovis,,,,,");
		
		assertEquals(diretores.size(), 1);
		assertEquals(diretores.get(0).getNome(), "Michael Slovis");
	}
	
	@Test
	void deveObterArrayContendoAtributosDeUmaClasseComListaVazia() {
		assertEquals(obterArrayContendoDadosDoAtributoDeUmaClasse(Diretor::getNome, List.of()).length, 0);
	}
	
	@Test
	void deveObterArrayContendoAtributosDeUmaClasse() {
		List<Diretor> diretores = List.of(
				new Diretor("Christopher Nolan"),
				new Diretor("Vince Gilligan"));
		
		String[] arrayEsperado = {diretores.get(0).getNome(), diretores.get(1).getNome()};

		assertArrayEquals(obterArrayContendoDadosDoAtributoDeUmaClasse(Diretor::getNome, diretores), arrayEsperado);
	}
	
	@Test
	void deveConverterListaDeStringParaArray() {
		List<String> nomes = List.of("Time Flies", "Landed");
		String[] arrayEsperado = {nomes.get(0), nomes.get(1)};
		
		assertArrayEquals(converterListaDeStringParaArray(nomes), arrayEsperado);
	}
	
	@Test
	void deveConverterListaDeStringParaArrayRetornandoArrayVazio() {
		assertEquals(converterListaDeStringParaArray(List.of()).length, 0);
	}
}
