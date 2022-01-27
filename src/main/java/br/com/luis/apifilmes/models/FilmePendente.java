package br.com.luis.apifilmes.models;

import java.util.ArrayList;
import java.util.List;

public class FilmePendente extends Filme {
	private String titulo;
	private int anoDeLancamento;
	private List<Diretor> diretores = new ArrayList<>();
	private List<Genero> generos = new ArrayList<>();
	private Idioma idioma;
	private int runtime;

	public FilmePendente(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos,
			Idioma idioma, int runtime) {
		super(titulo, anoDeLancamento, diretores, generos, idioma, runtime);
	}

	@Override
	public String getTitulo() {
		return super.getTitulo();
	}

	@Override
	public int getAnoDeLancamento() {
		return super.getAnoDeLancamento();
	}

	@Override
	public List<Diretor> getDiretores() {
		return super.getDiretores();
	}

	@Override
	public Idioma getIdioma() {
		return super.getIdioma();
	}

	@Override
	public List<Genero> getGeneros() {
		return super.getGeneros();
	}

	@Override
	public int getRuntime() {
		return super.getRuntime();
	}

	@Override
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

}
