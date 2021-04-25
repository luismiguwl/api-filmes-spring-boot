package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Mes;

public class FilmeUtils {
	public static List<Filme> buscarFilmePorPalavra(List<Filme> filmes, String chave) {
		return filmes.stream()
				.filter(filme -> verificarSeExistePalavraNosDadosDoFilme(filme, chave))
				.collect(Collectors.toList());
	}


	private static String mesclarTituloComDiretores(Filme filme) {
		String corpo = filme.getTitulo() + " ";

		if (filme.getDiretores().isEmpty()) {
			corpo += filme.getDiretor().getNome() + " ";
			return corpo.trim();
		}
		
		corpo += DiretorUtils.mesclarTodosOsDiretores(filme.getDiretores());
		return corpo;
	}

	public static boolean verificarSeFilmeFoiVistoNoMes(Filme filme, Mes mes) {
		return filme.getMes().getNome().equals(mes.getNome());
	}

	private static boolean verificarSeExistePalavraNosDadosDoFilme(Filme filme, String chave) {
		String tituloDoFilmeConcatenadoComDiretores = mesclarTituloComDiretores(filme).toLowerCase();
		
		chave = chave.toLowerCase();
		return tituloDoFilmeConcatenadoComDiretores.contains(chave);
	}
}