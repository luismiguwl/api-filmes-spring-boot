package br.com.luis.apifilmes.models;

import br.com.luis.apifilmes.models.utils.DiretorUtils;
import lombok.Data;

@Data
public class Diretor {
	private String nome;
	private int quantidadeDeFilmesVistos;

	public Diretor(String nome) {
		this.nome = nome;
	}
	
	public int getQuantidadeDeFilmesVistos() {
		quantidadeDeFilmesVistos = DiretorUtils.getQuantidadeDeFilmesVistos(this);
		return quantidadeDeFilmesVistos;
	}

}
