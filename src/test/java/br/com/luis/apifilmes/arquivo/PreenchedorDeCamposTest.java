package br.com.luis.apifilmes.arquivo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Plataforma;

public class PreenchedorDeCamposTest {

	PreenchedorDeCampos preenchedor;
	Joiner joiner = new Joiner();
	
	@Test
	public void deveRetornarListaContendoParaEscritaDadosDeUmFilmePendenteComUmDiretorEUmGenero() {
		FilmePendente filme = mock(FilmePendente.class);
		preenchedor = new PreenchedorDeCampos(filme);
		
		String titulo = "Tenet";
		Integer ano = 2020;
		Idioma idioma = new Idioma("Inglês");
		List<Diretor> diretores = List.of(new Diretor("Christopher Nolan"));
		List<Genero> generos = List.of(new Genero("Ação"));
		Duracao runtime = new Duracao(150);
		
		when(filme.getTitulo()).thenReturn(titulo);
		when(filme.getAnoDeLancamento()).thenReturn(ano);
		when(filme.getIdioma()).thenReturn(idioma);
		when(filme.getDiretores()).thenReturn(diretores);
		when(filme.getGeneros()).thenReturn(generos);
		when(filme.getRuntime()).thenReturn(runtime);
		when(filme.getDataEmQueFoiAdicionado()).thenReturn(null);
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					filme.getTitulo(),
					Integer.toString(filme.getAnoDeLancamento()),
					filme.getIdioma().getNome(),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome),
					filme.getRuntime().getDuracaoEmMinutos(),
					filme.getDataEmQueFoiAdicionado() != null ? filme.getDataEmQueFoiAdicionado() : "" 
				);
		
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void deveRetornarListaContendoDadosParaEscritaDeUmFilmePendenteComMaisDeUmDiretorEVariosGeneros() {
		FilmePendente filme = mock(FilmePendente.class);
		preenchedor = new PreenchedorDeCampos(filme);
		
		String titulo = "Shrek";
		Integer ano = 2020;
		Idioma idioma = new Idioma("Inglês");
		List<Diretor> diretores = List.of(new Diretor("Andrew Adamson"), new Diretor("Vicky Jenson"));
		List<Genero> generos = List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller"));
		Duracao runtime = new Duracao(150);
		
		when(filme.getTitulo()).thenReturn(titulo);
		when(filme.getAnoDeLancamento()).thenReturn(ano);
		when(filme.getIdioma()).thenReturn(idioma);
		when(filme.getDiretores()).thenReturn(diretores);
		when(filme.getGeneros()).thenReturn(generos);
		when(filme.getRuntime()).thenReturn(runtime);
		when(filme.getDataEmQueFoiAdicionado()).thenReturn(null);
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					filme.getTitulo(),
					Integer.toString(filme.getAnoDeLancamento()),
					filme.getIdioma().getNome(),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome),
					filme.getRuntime().getDuracaoEmMinutos(),
					filme.getDataEmQueFoiAdicionado() != null ? filme.getDataEmQueFoiAdicionado() : "" 
				);
		
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void deveRetornarListaContendoDadosParaEscritaDeUmFilmePendenteComDataDeAdicaoPossuindoValor() {
		FilmePendente filme = mock(FilmePendente.class);
		preenchedor = new PreenchedorDeCampos(filme);
		
		String titulo = "Shrek";
		Integer ano = 2020;
		Idioma idioma = new Idioma("Inglês");
		List<Diretor> diretores = List.of(new Diretor("Andrew Adamson"), new Diretor("Vicky Jenson"));
		List<Genero> generos = List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller"));
		Duracao runtime = new Duracao(150);
		String data = "15/02/2022";
		
		when(filme.getTitulo()).thenReturn(titulo);
		when(filme.getAnoDeLancamento()).thenReturn(ano);
		when(filme.getIdioma()).thenReturn(idioma);
		when(filme.getDiretores()).thenReturn(diretores);
		when(filme.getGeneros()).thenReturn(generos);
		when(filme.getRuntime()).thenReturn(runtime);
		when(filme.getDataEmQueFoiAdicionado()).thenReturn(data);
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					filme.getTitulo(),
					Integer.toString(filme.getAnoDeLancamento()),
					filme.getIdioma().getNome(),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome),
					filme.getRuntime().getDuracaoEmMinutos(),
					filme.getDataEmQueFoiAdicionado() != null ? filme.getDataEmQueFoiAdicionado() : "" 
				);
		
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void deveRetornarListaContendoDadosParaEscritaDeUmFilmePendenteComRuntimeNulo() {
		FilmePendente filme = mock(FilmePendente.class);
		preenchedor = new PreenchedorDeCampos(filme);
		
		String titulo = "Shrek";
		Integer ano = 2020;
		Idioma idioma = new Idioma("Inglês");
		List<Diretor> diretores = List.of(new Diretor("Andrew Adamson"), new Diretor("Vicky Jenson"));
		List<Genero> generos = List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller"));
		Duracao runtime = null;
		String data = "15/02/2022";
		
		when(filme.getTitulo()).thenReturn(titulo);
		when(filme.getAnoDeLancamento()).thenReturn(ano);
		when(filme.getIdioma()).thenReturn(idioma);
		when(filme.getDiretores()).thenReturn(diretores);
		when(filme.getGeneros()).thenReturn(generos);
		when(filme.getRuntime()).thenReturn(runtime);
		when(filme.getDataEmQueFoiAdicionado()).thenReturn(data);
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					filme.getTitulo(),
					Integer.toString(filme.getAnoDeLancamento()),
					filme.getIdioma().getNome(),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome),
					filme.getRuntime() != null ? filme.getRuntime().getDuracaoEmMinutos() : 0,
					filme.getDataEmQueFoiAdicionado() != null ? filme.getDataEmQueFoiAdicionado() : "" 
				);
		
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void deveRetornarListaContendoDadosParaEscritaDeUmFilmeVisto() {
		FilmeVisto filme = mock(FilmeVisto.class);
		preenchedor = new PreenchedorDeCampos(filme);
		
		String titulo = "Tenet";
		Integer ano = 2020;
		Idioma idioma = new Idioma("Inglês");
		List<Diretor> diretores = List.of(new Diretor("Christopher Nolan"));
		List<Genero> generos = List.of(new Genero("Ação"), new Genero("Ficção científica"), new Genero("Thriller"));
		Duracao runtime = new Duracao(150);
		Plataforma plataforma = Plataforma.HBO_MAX;
		boolean assistidoLegendado = true;
		String data = "15/02/2022";
		
		when(filme.getTitulo()).thenReturn(titulo);
		when(filme.getAnoDeLancamento()).thenReturn(ano);
		when(filme.getIdioma()).thenReturn(idioma);
		when(filme.getDiretores()).thenReturn(diretores);
		when(filme.getGeneros()).thenReturn(generos);
		when(filme.getRuntime()).thenReturn(runtime);
		when(filme.getAssistidoLegendado()).thenReturn(assistidoLegendado);
		when(filme.getPlataformaEmQueFoiAssistido()).thenReturn(plataforma);
		when(filme.getDataEmQueFoiAssistido()).thenReturn(data);
		
		List<Object> listaRecebida = preenchedor.preencher();
		List<Object> listaEsperada = List.of(
					filme.getTitulo(),
					filme.getDataEmQueFoiAssistido(),
					Integer.toString(filme.getAnoDeLancamento()),
					filme.getIdioma().getNome(),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome),
					joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome),
					filme.getRuntime().getDuracaoEmMinutos(),
					filme.getPlataformaEmQueFoiAssistido().getNomes()[0],
					filme.getAssistidoLegendado()
				);
		
		assertEquals(listaRecebida, listaEsperada);
	}
	
	@Test
	public void deveRetornarTrueSeForFilmePendente() {
		FilmePendente filme = mock(FilmePendente.class);
		preenchedor = new PreenchedorDeCampos(filme);
		assertTrue(preenchedor.ehFilmePendente());
	}
	
	@Test
	public void deveRetornarFalseSeForFilmeVisto() {
		FilmeVisto filme = mock(FilmeVisto.class);
		preenchedor = new PreenchedorDeCampos(filme);
		assertFalse(preenchedor.ehFilmePendente());
	}
	
}
 