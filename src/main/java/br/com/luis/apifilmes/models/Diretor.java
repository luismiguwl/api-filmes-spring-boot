package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.utils.DiretorUtils;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Diretor {
	private String nome;
	private Integer quantidadeDeFilmesVistos;

	public Diretor(String nome) {
		this.nome = nome;
	}
	
	public Integer getQuantidadeDeFilmesVistos() {
		quantidadeDeFilmesVistos = DiretorUtils.getQuantidadeDeFilmesVistos(this);
		return quantidadeDeFilmesVistos > 0 ? quantidadeDeFilmesVistos : null;
	}

}
