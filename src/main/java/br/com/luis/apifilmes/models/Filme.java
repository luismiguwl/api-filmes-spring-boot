package br.com.luis.apifilmes.models;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.models.enums.Plataforma;
import br.com.luis.apifilmes.models.utils.DiretorUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Filme {
	private String titulo;
	private int anoDeLancamento;
	private String dataEmQueFoiAssistido;
	private List<Diretor> diretores = new ArrayList<>();
	private List<Genero> generos = new ArrayList<>();
	private Idioma idioma;
	private int runtime;
	private Plataforma plataformaEmQueFoiAssistido;
	private Boolean assistidoLegendado;

	public Filme(String titulo, int anoDeLancamento, String dataEmQueFoiAssistido,
			List<Diretor> diretores, List<Genero> generos, Idioma idioma, int runtime, Plataforma plataforma, Boolean assistidoLegendado) {
		this.titulo = titulo;
		this.anoDeLancamento = anoDeLancamento;
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
		this.diretores = diretores;
		this.generos = generos;
		this.runtime = runtime;
		this.idioma = idioma;
		this.plataformaEmQueFoiAssistido = plataforma;
		this.assistidoLegendado = assistidoLegendado;
	}

	public Filme() {
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public String getDataEmQueFoiAssistido() {
		return dataEmQueFoiAssistido;
	}

	public List<Diretor> getDiretores() {
		return diretores;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public int getRuntime() {
		return runtime;
	}

	public String getPlataformaEmQueFoiAssistido() {
		return plataformaEmQueFoiAssistido.getPlataforma();
	}

	public boolean foiAssistidoLegendado() {
		return assistidoLegendado;
	}

	public String mesclarTituloComDiretores() {
		return getTitulo() + " " + DiretorUtils.mesclarTodosOsDiretores(diretores);
	}
}
