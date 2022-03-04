package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;

public class FilmeUtils {
	public static List<Filme> buscarFilmePorPalavra(List<? extends Filme> filmes, String chave) {
		if (chave.trim().isEmpty()) {
			return List.of();
		}

		Predicate<Filme> filmeContemChave = filme -> {
			String dadosDoFilme = filme.mesclarTituloComDiretores();
			return dadosDoFilme.toLowerCase().contains(chave.toLowerCase().trim());
		};
		
		return filmes.stream()
				.filter(filmeContemChave)
				.collect(Collectors.toList());
	}
}