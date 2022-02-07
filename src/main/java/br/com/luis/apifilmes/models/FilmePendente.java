package br.com.luis.apifilmes.models;

import java.util.List;

public class FilmePendente extends Filme {
	private Duracao runtime;
	private String dataEmQueFoiAdicionado;

	public FilmePendente(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos,
			Idioma idioma, Duracao runtime, String dataEmQueFoiAdicionado) {
		super(titulo, anoDeLancamento, diretores, generos, idioma);
		this.runtime = runtime;
		this.dataEmQueFoiAdicionado = dataEmQueFoiAdicionado;
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

	public Duracao getRuntime() {
		return runtime;
	}
	
	public String getDataEmQueFoiAdicionado() {
		return dataEmQueFoiAdicionado;
	}

	@Override
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

}
