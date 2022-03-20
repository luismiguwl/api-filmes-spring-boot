package br.com.luis.apifilmes.arquivo;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.*;

class GeradorDeCabecalhoTest {
	
	private final String[] CABECALHO_FILME_PENDENTE = "titulo,anoDeLancamento,idioma,diretor,genero,duracao,dataEmQueFoiAdicionado".split(",");
	private final String[] CABECALHO_FILME_VISTO = "titulo,dataAssistido,anoDeLancamento,idioma,diretor,genero,duracao,plataforma,assistidoLegendado".split(",");
	
	GeradorDeCabecalho gerador = new GeradorDeCabecalho(new FilmeVisto());
	
	@Test
	void deveGerarCabecalhoParaFilmePendente() {
		gerador.setFilme(new FilmePendente());
		assertArrayEquals(gerador.gerar(), CABECALHO_FILME_PENDENTE);
	}

	@Test
	void deveLancarNullPointerExceptionSeFilmeForNulo() {
		gerador.setFilme(null);
		assertThrows(NullPointerException.class, () -> gerador.gerar());
	}
	
	@Test
	void deveGerarCabecalhoParaFilmeVisto() {
		assertArrayEquals(gerador.gerar(), CABECALHO_FILME_VISTO);
	}
}
