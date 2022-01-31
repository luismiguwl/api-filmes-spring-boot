package br.com.luis.apifilmes.models;

import java.util.List;

public class FilmePendente extends Filme {
	private Integer runtime;

	public FilmePendente(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos,
			Idioma idioma, Integer runtime) {
		super(titulo, anoDeLancamento, diretores, generos, idioma);
		this.runtime = runtime;
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

	public Integer getRuntime() {
		return runtime;
	}

	@Override
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

}
