package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;

public class FilmeUtils {
	public static List<? extends Filme> buscarFilmePorPalavra(List<? extends Filme> filmes, String chave) {
		return filmes.stream()
				.filter(filme -> verificarSeExistePalavraNosDadosDoFilme(filme, chave))
				.collect(Collectors.toList());
	}

	private static boolean verificarSeExistePalavraNosDadosDoFilme(Filme filme, String chave) {
		String tituloDoFilmeConcatenadoComNomeDosDiretores = filme.mesclarTituloComDiretores().toLowerCase();
		
		chave = chave.toLowerCase();
		return tituloDoFilmeConcatenadoComNomeDosDiretores.contains(chave);
	}

	public static boolean buscarPorIntervaloDeAnos(Filme filme, int de, int ate) {
		return filme.getAnoDeLancamento() >= de && filme.getAnoDeLancamento() <= ate;
	}

	public static boolean buscarPorAnoDeLancamento(Filme filme, int ano) {
		return filme.getAnoDeLancamento() == ano;
	}
}