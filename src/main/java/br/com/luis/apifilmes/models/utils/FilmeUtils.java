package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Mes;

public class FilmeUtils {
	public static List<Filme> buscarFilmePorPalavra(List<Filme> filmes, String chave) {
		return filmes.stream().filter(filme -> mesclarDadosDeUmFilme(filme).toLowerCase().contains(chave.toLowerCase()))
				.collect(Collectors.toList());
	}

	private static String mesclarDadosDeUmFilme(Filme filme) {
		String corpo = filme.getTitulo() + " ";

		if (filme.getDiretores().isEmpty()) {
			corpo += filme.getDiretor().getNome() + " ";
		} else {
			corpo += DiretorUtils.mesclarTodosOsDiretores(filme.getDiretores());
		}

		return corpo.trim();
	}

	public static boolean verificarSeFilmeFoiVistoNoMes(Filme filme, Mes mes) {
		return filme.getMes().getNome().equals(mes.getNome());
	}

}