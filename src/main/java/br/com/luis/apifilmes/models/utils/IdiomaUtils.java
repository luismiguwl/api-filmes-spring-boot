package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Coluna;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

public class IdiomaUtils {
	private static List<String> idiomas = Mapeamento.getDadosDaColuna(Coluna.IDIOMA);
	
	
	public static String getAbreviacao(String idioma) {
		List<String> listaDeAbreviacoes = Mapeamento.getAbreviacoes();
		return listaDeAbreviacoes.stream()
				.filter(abreviacao -> abreviacao.split(",")[0].equals(idioma))
				.map(abreviacao -> abreviacao.split(",")[1]).collect(Collectors.toList())
				.get(0).toLowerCase();
	}

	private static String getQuantidadeDeFilmes(String idioma) {
		String corpo = "";

		int quantidade = (int) idiomas.stream().filter(i -> i.equalsIgnoreCase(idioma))
				.count();
		if (quantidade == 1) {
			corpo += quantidade + " filme visto em " + idioma;
		} else {
			corpo += quantidade + " filmes vistos em " + idioma;
		}

		corpo += " (aprox. " + Calculadora.calcularPorcentagem(idiomas.size(), quantidade) + "%)";

		return corpo;
	}

	private static List<String> getIdiomasDistintos() {
		return idiomas.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	public static List<String> definirQuantidadeDeFilmesEmDeterminadoIdioma() {
		return getIdiomasDistintos().stream()
				.map(filme -> getQuantidadeDeFilmes(filme))
				.collect(Collectors.toList());
	}

	public static boolean filtrarPorIdioma(Filme filme, String idioma) {
		return filme.getIdioma().getAbreviacao().startsWith(idioma);
	}
}