package br.com.luis.api.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.FilmeUtils;
import br.com.luis.api.models.utils.MesUtils;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Filme {
	private String titulo;
	private int ano;

	private String data;
	private Idioma idioma;
	private Diretor diretor;
	private String genero;
	private List<Diretor> diretores = new ArrayList<>();

	@JsonIgnore
	private Mes mes;

	public Mes getMes() {
		return MesUtils.definirDadosDoMes(this);
	}

	public Filme(String titulo, int ano, String data, Idioma idioma, Diretor diretor, String genero,
			List<Diretor> diretores) {
		super();
		this.titulo = titulo;
		this.ano = ano;
		this.data = data;
		this.idioma = idioma;
		this.diretor = diretor;
		this.genero = genero;
		this.diretores = diretores;
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

		return corpo;
	}
}
