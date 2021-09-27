package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.utils.DiretorUtils;

@JsonInclude(Include.NON_NULL)
public class Diretor {
	private String nome;

	public Diretor(String nome) {
		this.nome = nome;
	}

	public Diretor() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidadeDeFilmesVistos() {
		int quantidadeDeFilmesVistos = DiretorUtils.getQuantidadeDeFilmesVistos(this);
		return quantidadeDeFilmesVistos > 0 ? quantidadeDeFilmesVistos : null;
	}
}
