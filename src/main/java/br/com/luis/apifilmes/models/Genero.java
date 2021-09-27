package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luis.apifilmes.models.utils.GeneroUtils;

public class Genero {
	private String nome;
	
	@JsonIgnore
	private Integer quantidadeDeFilmes;
	
	public Genero(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Integer getQuantidadeDeFilmes() {
		quantidadeDeFilmes = GeneroUtils.getQuantidadeDeFilmes(this);
		return quantidadeDeFilmes;
	}
}
