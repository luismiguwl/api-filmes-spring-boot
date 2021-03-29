package br.com.luis.api.models;

import br.com.luis.api.models.utils.DiretorUtils;

public class Diretor {
	private String nome;
	private int quantidadeDeFilmesNaLista;

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
		quantidadeDeFilmesNaLista = DiretorUtils.definirQuantidadeDeFilmesDirigidos(this.nome);
		return quantidadeDeFilmesNaLista;
	}

	public void setQuantidadeDeFilmes(int quantidadeDeFilmesNaLista) {
		this.quantidadeDeFilmesNaLista = quantidadeDeFilmesNaLista;
	}
	
	@Override
	public String toString() {
		return nome + "," + getQuantidadeDeFilmes() + " filmes";
	}
}
