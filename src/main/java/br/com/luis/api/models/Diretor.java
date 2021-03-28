package br.com.luis.api.models;

import br.com.luis.api.models.utils.DiretorUtils;

public class Diretor {
	private String nome;
	private int quantidadeDeFilmes;

	public Diretor(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome.trim();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidadeDeFilmes() {
		quantidadeDeFilmes = DiretorUtils.definirQuantidadeDeFilmesDirigidos(this.nome);
		return quantidadeDeFilmes;
	}

	public void setQuantidadeDeFilmes(int quantidadeDeFilmes) {
		this.quantidadeDeFilmes = quantidadeDeFilmes;
	}
	
	@Override
	public String toString() {
		return nome + "," + getQuantidadeDeFilmes() + " filmes";
	}
}
