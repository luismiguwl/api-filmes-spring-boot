package br.com.luis.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.DiretorUtils;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Diretor {
	private String nome;
	private int quantidadeDeFilmesVistos;

	public Diretor(String nome) {
		this.nome = nome;
	}
	
	public int getQuantidadeDeFilmesVistos() {
		return DiretorUtils.getQuantidadeDeFilmesVistos(this);
	}

}
