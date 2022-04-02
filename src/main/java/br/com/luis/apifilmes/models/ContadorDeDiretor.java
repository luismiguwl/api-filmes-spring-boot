package br.com.luis.apifilmes.models;

import java.util.*;

public class ContadorDeDiretor {
	public List<Diretor> definirOcorrencias(List<Diretor> diretores, String[] nomeDosDiretoresDeFilmesVistos, String... nomeDosDiretoresDeFilmesPendentes) {
		for (Diretor diretor : diretores) {
			int filmesVistos = obterQuantidadeDeFilmesDoDiretor(diretor.getNome(), nomeDosDiretoresDeFilmesVistos);
			if (filmesVistos > 0) {
				diretor.setQuantidadeDeFilmesVistos(filmesVistos);
			}
			
			int filmesPendentes = obterQuantidadeDeFilmesDoDiretor(diretor.getNome(), nomeDosDiretoresDeFilmesPendentes);
			if (filmesPendentes > 0) {
				diretor.setQuantidadeDeFilmesPendentes(filmesPendentes);
			}
		}
		
		return diretores;
	}

	private int obterQuantidadeDeFilmesDoDiretor(String nomeDoDiretor, String[] nomes) {
		return (int) Arrays.stream(nomes)
				.filter(nome -> nome.equals(nomeDoDiretor))
				.count();
	}
	
}
