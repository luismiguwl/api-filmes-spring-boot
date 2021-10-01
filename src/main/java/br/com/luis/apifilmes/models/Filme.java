package br.com.luis.apifilmes.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(int anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}

	public String getDataEmQueFoiAssistido() {
		return dataEmQueFoiAssistido;
	}

	public void setDataEmQueFoiAssistido(String dataEmQueFoiAssistido) {
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public List<Diretor> getDiretores() {
		return diretores;
	}

	public void setDiretores(List<Diretor> diretores) {
		this.diretores = diretores;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String mesclarTituloComDiretores() {
		String corpo = getTitulo();

		corpo += getDiretores().stream()
				.map(diretor -> diretor.getNome())
				.collect(Collectors.joining());

		return corpo;
	}
}
