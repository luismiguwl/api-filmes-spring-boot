package br.com.luis.apifilmes.models;

import java.util.*;
import br.com.luis.apifilmes.models.utils.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.*;

@JsonInclude(Include.NON_EMPTY)
public abstract class Filme {
	private String titulo;
	private int anoDeLancamento;
	private List<Diretor> diretores = new ArrayList<>();
	private List<Genero> generos = new ArrayList<>();
	private Idioma idioma;
	private Duracao runtime;

	public Filme(String titulo, int anoDeLancamento, List<Diretor> diretores, List<Genero> generos, Idioma idioma, Duracao runtime) {
		this.titulo = titulo;
		this.anoDeLancamento = anoDeLancamento;
		this.diretores = diretores;
		this.generos = generos;
		this.idioma = idioma;
		this.runtime = runtime;
	}

	public Filme() {
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(int ano) {
		this.anoDeLancamento = ano;
	}

	public List<Diretor> getDiretores() {
		return diretores;
	}
	
	public void setDiretores(List<Diretor> diretores) {
		this.diretores = diretores;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public Duracao getRuntime() {
		return runtime;
	}

	public void setRuntime(Duracao runtime) {
		this.runtime = runtime;
	}
	
	public String mesclarTituloComDiretores() {
		return getTitulo() + " " + DiretorUtils.mesclarTodosOsDiretores(diretores);
	}
	
	public boolean anoDeLancamentoEstaEntre(int anoMinimo, int anoMaximo) {
		int anoMenor, anoMaior;

		if (anoMinimo > anoMaximo) {
			anoMenor = anoMaximo;
			anoMaior = anoMinimo;
		} else {
			anoMenor = anoMinimo;
			anoMaior = anoMaximo;
		}

		return anoDeLancamento >= anoMenor && anoDeLancamento <= anoMaior;
	}
	
}
