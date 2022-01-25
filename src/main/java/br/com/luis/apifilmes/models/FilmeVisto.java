package br.com.luis.apifilmes.models;

import java.util.List;

import br.com.luis.apifilmes.models.enums.Plataforma;

public class FilmeVisto extends Filme {
	private String dataEmQueFoiAssistido;
	private Plataforma plataformaEmQueFoiAssistido;
	private Boolean assistidoLegendado;

	public FilmeVisto(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos, Idioma idioma,
			int runtime, String dataEmQueFoiAssistido, Plataforma plataformaEmQueFoiAssistido,
			Boolean assistidoLegendado) {
		super(titulo, anoDeLancamento, diretores, generos, idioma, runtime);
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
		this.plataformaEmQueFoiAssistido = plataformaEmQueFoiAssistido;
		this.assistidoLegendado = assistidoLegendado;
	}

	public FilmeVisto() {
	}

	public String getDataEmQueFoiAssistido() {
		return dataEmQueFoiAssistido;
	}

	public Plataforma getPlataformaEmQueFoiAssistido() {
		return plataformaEmQueFoiAssistido;
	}

	public Boolean getAssistidoLegendado() {
		return assistidoLegendado;
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
