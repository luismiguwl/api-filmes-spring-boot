package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luis.apifilmes.models.utils.GeneroUtils;

public class Genero {
	private String nome;
	
	@JsonIgnore
	private Integer quantidadeDeFilmes;
	
	public Genero() {
	}
	
	public Genero(String nome) {
		this.nome = nome;
	}

	public Genero(String nome, int quantidadeDeFilmes) {
		this.nome = nome;
		this.quantidadeDeFilmes = quantidadeDeFilmes;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Integer getQuantidadeDeFilmes() {
		return quantidadeDeFilmes;
	}
	
	public void setQuantidadeDeFilmes(Integer quantidadeDeFilmes) {
		this.quantidadeDeFilmes = quantidadeDeFilmes;
	}
}
