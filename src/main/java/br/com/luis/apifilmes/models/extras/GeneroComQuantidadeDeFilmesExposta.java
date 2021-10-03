package br.com.luis.apifilmes.models.extras;

import br.com.luis.apifilmes.models.Genero;

public class GeneroComQuantidadeDeFilmesExposta {
	private String nome;
	private int quantidadeDeFilmes;
	
	public GeneroComQuantidadeDeFilmesExposta(Genero genero) {
		nome = genero.getNome();
		quantidadeDeFilmes = genero.getQuantidadeDeFilmes();
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getQuantidadeDeFilmes() {
		return quantidadeDeFilmes;
	}
}
