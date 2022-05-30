package br.com.luis.apifilmes.models;

import java.util.List;

public class FilmePendente extends Filme {
	private String dataEmQueFoiAdicionado;

	public FilmePendente(String titulo, Integer anoDeLancamento, List<Diretor> diretores, List<Genero> generos,
			Idioma idioma, Duracao runtime, String linkIMDB, String dataEmQueFoiAdicionado) {
		super(titulo, anoDeLancamento, diretores, generos, idioma, runtime, linkIMDB);
		this.dataEmQueFoiAdicionado = dataEmQueFoiAdicionado;
	}

	public FilmePendente() {
	}
	
	@Override
	public String getTitulo() {
		return super.getTitulo();
	}

	@Override
	public Integer getAnoDeLancamento() {
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
	public Duracao getRuntime() {
		return super.getRuntime();
	}
	
	@Override
	public String getLinkIMDB() {
		return super.getLinkIMDB();
	}
	
	public String getDataEmQueFoiAdicionado() {
		return dataEmQueFoiAdicionado;
	}

	public void setDataEmQueFoiAdicionado(String dataEmQueFoiAdicionado) {
		this.dataEmQueFoiAdicionado = dataEmQueFoiAdicionado;
	}

	@Override
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

}
