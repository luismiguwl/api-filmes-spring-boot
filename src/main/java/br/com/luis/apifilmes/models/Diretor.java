package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.utils.DiretorUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Diretor {
	private String nome;

	public Integer getQuantidadeDeFilmesVistos() {
		int quantidadeDeFilmesVistos = DiretorUtils.getQuantidadeDeFilmesVistos(this);
		return quantidadeDeFilmesVistos > 0 ? quantidadeDeFilmesVistos : null;
	}
}
