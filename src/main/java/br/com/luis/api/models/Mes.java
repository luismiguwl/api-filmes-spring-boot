package br.com.luis.api.models;

public class Mes {
	private String nome;
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
