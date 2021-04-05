package br.com.luis.api.models;

import br.com.luis.api.models.utils.DiretorUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
