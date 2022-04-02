package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Diretor {
	private String nome;
	private Integer totalDeFilmesVistos;
	private Integer totalDeFilmesPendentes;

	public Diretor(String nome) {
		this.nome = nome;
	}

	public Diretor(String nome, Integer totalDeFilmesVistos) {
		this.nome = nome;
		this.totalDeFilmesVistos = totalDeFilmesVistos;
	}

	public Diretor() {
	}

	public String getNome() {
		return nome;
	}

	public Integer getTotalDeFilmesVistos() {
		return totalDeFilmesVistos;
	}
	
	public void setQuantidadeDeFilmesVistos(Integer totalDeFilmesVistos) {
		this.totalDeFilmesVistos = totalDeFilmesVistos;
	}

	public Integer getTotalDeFilmesPendentes() {
		return totalDeFilmesPendentes;
	}

	public void setQuantidadeDeFilmesPendentes(Integer totalDeFilmesPendentes) {
		this.totalDeFilmesPendentes = totalDeFilmesPendentes;
	}
}
