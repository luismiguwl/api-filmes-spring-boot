package br.com.luis.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.IdiomaUtils;

@JsonInclude(Include.NON_EMPTY)
public class Idioma {
	private String nome;
	
	@JsonIgnore
	private String abreviacao;

	public Idioma() {
	}

	public Idioma(String nome) {
		super();
		this.nome = nome;
		abreviacao = definirAbreviacao();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
	private String definirAbreviacao() {
		return IdiomaUtils.getAbreviacao(nome);
	}

}
