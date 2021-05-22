package br.com.luis.apifilmes.models.extras;

import br.com.luis.apifilmes.models.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GeneroComQuantidadeDeFilmesExposta {
	private String nome;
	private int quantidadeDeFilmes;
	
	public GeneroComQuantidadeDeFilmesExposta(Genero genero) {
		nome = genero.getNome();
		quantidadeDeFilmes = genero.getQuantidadeDeFilmes();
	}
}
