package br.com.luis.apifilmes.models;

import java.util.List;

import br.com.luis.apifilmes.models.enums.Plataforma;

public class FilmeVisto extends Filme {
	private String dataEmQueFoiAssistido;
	private Plataforma plataformaEmQueFoiAssistido;
	private boolean assistidoLegendado;
	private int runtime;

	public FilmeVisto(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos, Idioma idioma,
			int runtime, String dataEmQueFoiAssistido, Plataforma plataformaEmQueFoiAssistido,
			boolean assistidoLegendado) {
		super(titulo, anoDeLancamento, diretores, generos, idioma);
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
		this.plataformaEmQueFoiAssistido = plataformaEmQueFoiAssistido;
		this.assistidoLegendado = assistidoLegendado;
		this.runtime = runtime;
	}

	public FilmeVisto() {
	}

	public String getDataEmQueFoiAssistido() {
		return dataEmQueFoiAssistido;
	}

	public Plataforma getPlataformaEmQueFoiAssistido() {
		return plataformaEmQueFoiAssistido;
	}

	public boolean getAssistidoLegendado() {
		return assistidoLegendado;
	}
	
	public int getRuntime() {
		return runtime;
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
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

	@Override
	public String toString() {
		return "FilmeVisto [dataEmQueFoiAssistido=" + dataEmQueFoiAssistido + ", plataformaEmQueFoiAssistido="
				+ plataformaEmQueFoiAssistido + ", assistidoLegendado=" + assistidoLegendado + ", runtime=" + runtime
				+ ", getDataEmQueFoiAssistido()=" + getDataEmQueFoiAssistido() + ", getPlataformaEmQueFoiAssistido()="
				+ getPlataformaEmQueFoiAssistido() + ", getAssistidoLegendado()=" + getAssistidoLegendado()
				+ ", getRuntime()=" + getRuntime() + ", getTitulo()=" + getTitulo() + ", getAnoDeLancamento()="
				+ getAnoDeLancamento() + ", getDiretores()=" + getDiretores() + ", getIdioma()=" + getIdioma()
				+ ", getGeneros()=" + getGeneros() + ", mesclarTituloComDiretores()=" + mesclarTituloComDiretores()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
