package br.com.luis.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.IdiomaUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_EMPTY)
@Data
public class Idioma {
	private String nome;

	@JsonIgnore
	private String abreviacao;

	public Idioma(String nome) {
		super();
		this.nome = nome;
		abreviacao = definirAbreviacao();
	}

	private String definirAbreviacao() {
		return IdiomaUtils.getAbreviacao(nome);
	}

}
