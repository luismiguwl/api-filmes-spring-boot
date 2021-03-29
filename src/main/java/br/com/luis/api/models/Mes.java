package br.com.luis.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Mes {
	private String nome;
	
	@JsonIgnore
	private int numeroDoMes;

	public Mes() {
	}

	public Mes(String nome, int numeroDoMes) {
		super();
		this.nome = nome;
		this.numeroDoMes = numeroDoMes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroDoMes() {
		return numeroDoMes;
	}

	public void setNumeroDoMes(int numeroDoMes) {
		this.numeroDoMes = numeroDoMes;
	}

}
