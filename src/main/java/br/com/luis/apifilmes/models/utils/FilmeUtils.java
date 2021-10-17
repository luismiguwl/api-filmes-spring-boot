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

	public static boolean verificarSeFilmeFoiVistoNoMes(Filme filme, Mes mes) {
		return filme.getMes().getNome().equals(mes.getNome());
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