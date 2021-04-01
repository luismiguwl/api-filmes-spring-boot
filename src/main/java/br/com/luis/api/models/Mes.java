package br.com.luis.api.models;

import lombok.Data;

@Data
public class Mes {
	private String nome;
	private int numeroDoMes;

	public Mes(String nome, int numeroDoMes) {
		super();
		this.nome = nome;
		this.numeroDoMes = numeroDoMes;
	}

}
