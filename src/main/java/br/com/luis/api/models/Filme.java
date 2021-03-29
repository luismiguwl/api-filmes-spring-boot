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
		super();
		this.titulo = titulo;
		this.data = data;
		this.ano = ano;
		this.idioma = idioma;
		this.diretor = diretor;
	}

	public Filme(String titulo, String data, int ano, Idioma idioma, List<Diretor> diretores) {
		super();
		this.titulo = titulo;
		this.data = data;
		this.ano = ano;
		this.idioma = idioma;
		this.diretores = diretores;
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

	@Override
	public String toString() {
		String corpo = "Título: " + titulo + "\n" + "Assistido em " + data + "\n" + "Lançado em " + ano + "\n"
				+ "Idioma: " + idioma + "\n" + listarDiretores();
		return corpo;
	}

	private String listarDiretores() {
		if (diretores.size() != 0) {
			String corpo = "Diretores: ";

			for (int i = 0; i < diretores.size(); i++) {
				corpo += "" + diretores.get(i).getNome();
				if (i != (diretores.size() - 1)) {
					corpo += ", ";
				}
			}

			return corpo;
		}
		return "Diretor: " + getDiretor().getNome();
	}

	public Mes getMes() {
		mes = MesUtils.definirDadosDoMes(this);
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

}
