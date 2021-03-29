package br.com.luis.api.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.FilmeUtils;
import br.com.luis.api.models.utils.MesUtils;

@JsonInclude(Include.NON_EMPTY)
public class Filme {
	private String titulo;
	private String data;
	private Mes mes;
	private int ano;
	private Idioma idioma;
	private Diretor diretor;
	private List<Diretor> diretores = new ArrayList<>();

	public Filme(String titulo, String data, int ano, Idioma idioma, Diretor diretor) {
		this.titulo = titulo;
		this.data = data;
		this.ano = ano;
		this.idioma = idioma;
		this.diretor = diretor;
	}

	public Filme(String titulo, String data, int ano, Idioma idioma, List<Diretor> diretores) {
		this.titulo = titulo;
		this.data = data;
		this.ano = ano;
		this.idioma = idioma;
		this.diretores = diretores;
	}

	public Filme(String titulo, int ano, Idioma idioma, List<Diretor> diretores) {
		this.titulo = titulo;
		this.ano = ano;
		this.idioma = idioma;
		this.diretores = diretores;
	}

	public Filme(String titulo, int ano, Idioma idioma, Diretor diretor) {
		this.titulo = titulo;
		this.ano = ano;
		this.idioma = idioma;
		this.diretor = diretor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}

	public List<Diretor> getDiretores() {
		return diretores;
	}

	public void setDiretores(List<Diretor> diretores) {
		this.diretores = diretores;
	}

	public Mes getMes() {
		return MesUtils.definirDadosDoMes(this);
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	@Override
	public String toString() {
		String corpo = "";

		corpo += "Título: " + titulo + "\n";
		
		if (data != null) {
			corpo += "Assistido em " + data + "\n";
		}
		
		corpo += "Lançado em " + ano + "\n";
		corpo += "Idioma: " + idioma.getNome() + "\n";
		corpo += FilmeUtils.listarDiretores(this);
		
		if (mes != null) {
			corpo += "Visto no mês de " + mes.getNome();
		}

		return corpo;
	}

}
