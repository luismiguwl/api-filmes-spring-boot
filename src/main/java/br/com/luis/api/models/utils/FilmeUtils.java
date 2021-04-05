package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.models.Filme;

public class FilmeUtils {
	public static String listarDiretores(Filme filme) {
		if (!filme.getDiretores().isEmpty()) {
			String corpo = "Diretores: ";

			for (int i = 0; i < filme.getDiretores().size(); i++) {
				corpo += "" + filme.getDiretores().get(i).getNome();
				if (i != (filme.getDiretores().size() - 1)) {
					corpo += ", ";
				}
			}

			return corpo;
		}
		
		return "Diretor: " + filme.getDiretor().getNome();
	}

	public static List<Filme> buscarFilmePorPalavra(List<Filme> filmes, String chave) {
		return filmes.stream()
				.filter(filme -> mesclarDadosDeUmFilme(filme).toLowerCase().contains(chave.toLowerCase()))
				.collect(Collectors.toList());
	}

	private static String mesclarDadosDeUmFilme(Filme filme) {
		String corpo = "";

		corpo += filme.getTitulo() + " ";

		if (filme.getDiretores().isEmpty()) {
			corpo += filme.getDiretor().getNome() + " ";
		} else {
			corpo += DiretorUtils.mesclarTodosOsDiretores(filme.getDiretores());
		}
		
		return corpo.trim();
	}

}