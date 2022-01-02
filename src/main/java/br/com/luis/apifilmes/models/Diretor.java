package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.models.utils.DiretorUtils;
import br.com.luis.apifilmes.utils.Mapeamento;

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
		int quantidadeDeFilmesVistos = DiretorUtils.getQuantidadeDeFilmesVistos(Mapeamento.getDadosDaColuna(Coluna.DIRETOR), this);
		return quantidadeDeFilmesVistos > 0 ? quantidadeDeFilmesVistos : null;
	}
}
