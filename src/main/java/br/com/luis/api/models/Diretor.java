package br.com.luis.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.DiretorUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_DEFAULT)
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
