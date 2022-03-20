package br.com.luis.apifilmes.arquivo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;

class PreenchedorDeCamposTest {

	PreenchedorDeCampos preenchedor;
	Joiner joiner = new Joiner();
	
	@Test
	void deveRetornarListaContendoParaEscritaDadosDeUmFilmePendenteComUmDiretorEUmGenero() {
		FilmeVisto filme = new FilmeVisto();
		preenchedor = new PreenchedorDeCampos(filme);
		
		filme.setTitulo("Tenet");
		filme.setAnoDeLancamento(2020);
		filme.setIdioma(new Idioma("Inglês"));
		filme.setDiretores(List.of(new Diretor("Christopher Nolan")));
		filme.setGeneros(List.of(new Genero("Ação")));
		filme.setRuntime(new Duracao(150));
		filme.setAssistidoLegendado(true);
		filme.setPlataformaEmQueFoiAssistido(Plataforma.NETFLIX);
		filme.setDataEmQueFoiAssistido("20/03/2022");
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					"Tenet",
					2020,
					"Inglês",
					"Christopher Nolan",
					"Ação",
					150,
					true,
					"Netflix",
					"20/03/2022"
				);

		for (Object elemento : listaRecebida) {
			assertTrue(listaEsperada.stream()
				.map(e -> e.toString())
				.collect(Collectors.toList())
				.contains(elemento.toString()));
		}
	}
	
	@Test
	void deveRetornarListaContendoDadosParaEscritaDeUmFilmePendenteComMaisDeUmDiretorEVariosGeneros() {
		Filme filme = new FilmePendente();
		preenchedor = new PreenchedorDeCampos(filme);
		
		filme.setTitulo("Tenet");
		filme.setAnoDeLancamento(2020);
		filme.setIdioma(new Idioma("Inglês"));
		filme.setDiretores(List.of(new Diretor("Andrew Adamson"), new Diretor("Vicky Jenson")));
		filme.setGeneros(List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller")));
		filme.setRuntime(new Duracao(150));
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					"Tenet",
					2020,
					"Inglês",
					"Andrew Adamson, Vicky Jenson",
					"Ação, Ficção científica, Thriller",
					150,
					"" 
				);

		for (Object elemento : listaRecebida) {
			assertTrue(listaEsperada.stream()
				.map(e -> e.toString())
				.collect(Collectors.toList())
				.contains(elemento.toString()));
		}
	}
	
	@Test
	void deveRetornarListaContendoDadosParaEscritaDeUmFilmePendenteComDataDeAdicaoPossuindoValor() {
		FilmePendente filme = new FilmePendente();
		preenchedor = new PreenchedorDeCampos(filme);

		filme.setTitulo("Tenet");
		filme.setAnoDeLancamento(2020);
		filme.setIdioma(new Idioma("Inglês"));
		filme.setDiretores(List.of(new Diretor("Andrew Adamson"), new Diretor("Vicky Jenson")));
		filme.setGeneros(List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller")));
		filme.setRuntime(new Duracao(150));
		filme.setDataEmQueFoiAdicionado("15/02/2022");

		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					"Tenet",
					2020,
					"Inglês",
					"Andrew Adamson, Vicky Jenson",
					"Ação, Ficção científica, Thriller",
					150,
					"15/02/2022"
				);
		
		for (Object elemento : listaRecebida) {
			assertTrue(listaEsperada.stream()
				.map(e -> e.toString())
				.collect(Collectors.toList())
				.contains(elemento.toString()));
		}
	}
	
	@Test
	void deveRetornarListaContendoDadosParaEscritaDeUmFilmePendenteComRuntimeNulo() {
		FilmePendente filme = new FilmePendente();
		preenchedor = new PreenchedorDeCampos(filme);

		filme.setTitulo("Tenet");
		filme.setAnoDeLancamento(2020);
		filme.setIdioma(new Idioma("Inglês"));
		filme.setDiretores(List.of(new Diretor("Andrew Adamson"), new Diretor("Vicky Jenson")));
		filme.setGeneros(List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller")));
		filme.setRuntime(null);
		filme.setDataEmQueFoiAdicionado("15/02/2022");
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					"Tenet",
					2020,
					"Inglês",
					"Andrew Adamson, Vicky Jenson",
					"Ação, Ficção científica, Thriller",
					0,
					"15/02/2022"
				);
		
		for (Object elemento : listaRecebida) {
			assertTrue(listaEsperada.stream()
				.map(e -> e.toString())
				.collect(Collectors.toList())
				.contains(elemento.toString()));
		}
	}
	
	@Test
	void deveRetornarTrueSeForFilmePendente() {
		assertTrue(new PreenchedorDeCampos(new FilmePendente()).ehFilmePendente());
	}
	
	@Test
	void deveRetornarFalseSeForFilmeVisto() {
		assertFalse(new PreenchedorDeCampos(new FilmeVisto()).ehFilmePendente());
	}

}
