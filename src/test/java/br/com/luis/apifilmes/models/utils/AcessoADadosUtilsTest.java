package br.com.luis.apifilmes.models.utils;

import static org.junit.jupiter.api.Assertions.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.Diretor;

class AcessoADadosUtilsTest {
	
	@Test
	void deveRetornarListaVaziaAoTentarConverterUmArrayVazio() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, new String[]{});
		assertTrue(diretores.isEmpty());
	}

	@Test
	void deveRetornarListaVaziaAoTentarConverterUmArrayComUmaStringVazia() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, "");
		assertTrue(diretores.isEmpty());
	}

	@Test
	void deveRetornarListaVaziaQuandoHouverApenasUmaStringQueContemVirgula() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, ",");
		assertTrue(diretores.isEmpty());
	}

	@Test
	void deveRetornarListaVaziaQuandoHouverVariasStringQuePossuemApenasVirgula() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, ",,,,,,,,");
		assertTrue(diretores.isEmpty());
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
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, "Michael Slovis");
		assertEquals(diretores.get(0).getNome(), "Michael Slovis");
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
		String[] arrayRecebido = obterArrayContendoDadosDoAtributoDeUmaClasse(Diretor::getNome, List.of());
		assertEquals(arrayRecebido.length, 0);
	}
	
	@Test
	void deveObterArrayContendoAtributosDeUmaClasse() {
		List<Diretor> diretores = List.of(
				new Diretor("Christopher Nolan"),
				new Diretor("Vince Gilligan"));
		
		String[] arrayEsperado = {diretores.get(0).getNome(), diretores.get(1).getNome()};
		String[] arrayRecebido = obterArrayContendoDadosDoAtributoDeUmaClasse(Diretor::getNome, diretores);
		
		assertArrayEquals(arrayRecebido, arrayEsperado);
	}
	
	@Test
	void deveConverterListaDeStringParaArray() {
		List<String> nomes = List.of("Time Flies", "Landed");

		String[] arrayEsperado = {nomes.get(0), nomes.get(1)};
		String[] arrayRecebido = converterListaDeStringParaArray(nomes);
		
		assertArrayEquals(arrayRecebido, arrayEsperado);
	}
	
	@Test
	void deveConverterListaDeStringParaArrayRetornandoArrayVazio() {
		String[] arrayRecebido = converterListaDeStringParaArray(List.of());
		assertEquals(arrayRecebido.length, 0);
	}
}
