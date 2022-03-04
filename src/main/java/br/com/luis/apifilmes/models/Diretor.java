package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Diretor {
	private String nome;
	private Integer quantidadeDeFilmesVistos;

	public Diretor(String nome) {
		this.nome = nome;
	}

	public Diretor(String nome, Integer quantidadeDeFilmesVistos) {
		this.nome = nome;
		this.quantidadeDeFilmesVistos = quantidadeDeFilmesVistos;
	}

	public Diretor() {
	}

	public String getNome() {
		return nome;
	}

	public Integer getQuantidadeDeFilmesVistos() {
		return quantidadeDeFilmesVistos;
	}
	
	public void setQuantidadeDeFilmesVistos(Integer quantidadeDeFilmesVistos) {
		this.quantidadeDeFilmesVistos = quantidadeDeFilmesVistos;
	}
}
	