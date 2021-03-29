package br.com.luis.api.models;

import br.com.luis.api.models.utils.DiretorUtils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Diretor {
	private String nome;
	private int quantidadeDeFilmes;

	public Diretor(String nome) {
		this.nome = nome;
	}

	public int getQuantidadeDeFilmes() {
		quantidadeDeFilmes = DiretorUtils.definirQuantidadeDeFilmesDirigidos(nome);
		return quantidadeDeFilmes;
	}

}
