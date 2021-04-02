package br.com.luis.api.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.FilmeUtils;
import br.com.luis.api.models.utils.MesUtils;
import lombok.Data;

@JsonInclude(Include.NON_EMPTY)
@Data
public class Filme {
	private String titulo;
	private String data;
	
	@JsonIgnore
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

	public Mes getMes() {
		return MesUtils.definirDadosDoMes(this);
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
