package br.com.luis.apifilmes.models;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.models.utils.DiretorUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.utils.MesUtils;

@JsonInclude(Include.NON_EMPTY)
public class Filme {
	private String titulo;
	private int anoDeLancamento;
	private String dataEmQueFoiAssistido;
	private Idioma idioma;
	private List<Diretor> diretores = new ArrayList<>();
	private List<Genero> generos = new ArrayList<>();
	private int runtime;

	@JsonIgnore
	public Mes getMes() {
		return MesUtils.definirDadosDoMes(this);
	}

	public Filme(String titulo, int anoDeLancamento, String dataEmQueFoiAssistido, Idioma idioma,
			List<Diretor> diretores, List<Genero> generos, int runtime) {
		this.titulo = titulo;
		this.anoDeLancamento = anoDeLancamento;
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
		this.idioma = idioma;
		this.diretores = diretores;
		this.generos = generos;
		this.runtime = runtime;
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

	public Idioma getIdioma() {
		return idioma;
	}

	public List<Diretor> getDiretores() {
		return diretores;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public int getRuntime() {
		return runtime;
	}

	public String mesclarTituloComDiretores() {
		return getTitulo() + " " + DiretorUtils.mesclarTodosOsDiretores(diretores);
	}
}
