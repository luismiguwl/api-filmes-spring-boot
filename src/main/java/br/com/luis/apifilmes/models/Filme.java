package br.com.luis.apifilmes.models;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.models.utils.DiretorUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public abstract class Filme {
	private String titulo;
	private int anoDeLancamento;
	private List<Diretor> diretores = new ArrayList<>();
	private List<Genero> generos = new ArrayList<>();
	private Idioma idioma;
	private int runtime;

	public Filme(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos, Idioma idioma, int runtime) {
		this.titulo = titulo;
		this.anoDeLancamento = anoDeLancamento;
		this.diretores = diretores;
		this.generos = generos;
		this.runtime = runtime;
		this.idioma = idioma;
	}

	public Filme() {
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
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

	public String mesclarTituloComDiretores() {
		return getTitulo() + " " + DiretorUtils.mesclarTodosOsDiretores(diretores);
	}
}
