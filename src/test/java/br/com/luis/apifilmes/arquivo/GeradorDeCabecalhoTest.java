package br.com.luis.apifilmes.arquivo;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.*;

public class GeradorDeCabecalhoTest {
	
	private final String[] CABECALHO_FILME_PENDENTE = "titulo,anoDeLancamento,idioma,diretor,genero,duracao,dataEmQueFoiAdicionado".split(",");
	private final String[] CABECALHO_FILME_VISTO = "titulo,dataAssistido,anoDeLancamento,idioma,diretor,genero,duracao,plataforma,assistidoLegendado".split(",");
	
	Filme filme;
	GeradorDeCabecalho gerador;
	
	@BeforeEach
	public void setUp() {
		gerador = new GeradorDeCabecalho(filme);
	}
	
	@Test
	public void deveGerarCabecalhoParaFilmePendente() {
		filme = new FilmePendente();
		gerador = new GeradorDeCabecalho(filme);
		
		String[] cabecalhoGerado = gerador.gerar();
		assertArrayEquals(cabecalhoGerado, CABECALHO_FILME_PENDENTE);
	}
	
	@Test
	public void deveGerarCabecalhoParaFilmeVisto() {
		filme = new FilmeVisto();
		gerador = new GeradorDeCabecalho(filme);
		
		String[] cabecalhoGerado = gerador.gerar();
		assertArrayEquals(cabecalhoGerado, CABECALHO_FILME_VISTO);
	}
}
