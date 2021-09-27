package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luis.apifilmes.models.utils.IdiomaUtils;

public class Idioma {
	private String nome;

	@JsonIgnore
	private String abreviacao;

	public Idioma(String nome) {
		this.nome = nome;
		abreviacao = definirAbreviacao();
	}
	
	public String getAbreviacao() {
		return abreviacao;
	}

	private String definirAbreviacao() {
		return IdiomaUtils.getAbreviacao(nome);
	}

}
