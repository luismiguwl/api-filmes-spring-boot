package br.com.luis.apifilmes.models.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

public class IdiomaUtils {
	private static String[] idiomas = Mapeamento.getDadosDaColuna(Coluna.IDIOMA);
	
	public static String getAbreviacao(String idiomaAlvo) {
		String[] listaContendoIdiomaEAbreviacaoSeparadosPorVirgula = Mapeamento.getDadosDaColuna(Coluna.IDIOMA, Coluna.ABREVIACAO);

		for (String idiomaEAbreviacao : listaContendoIdiomaEAbreviacaoSeparadosPorVirgula) {
			String[] idiomaEAbreviacaoSeparadosPorVirgula = idiomaEAbreviacao.split(",");

			String idioma = idiomaEAbreviacaoSeparadosPorVirgula[0];
			String abreviacao = idiomaEAbreviacaoSeparadosPorVirgula[1];

			if (idioma == idiomaAlvo) {
				return abreviacao;
			}
		}

		return null;
	}

	public static List<String> definirQuantidadeDeFilmesEmDeterminadoIdioma() {
		List<String> idiomasDistintos = getIdiomasDistintos();

		return idiomasDistintos.stream()
				.map(idioma -> getQuantidadeDeFilmes(idioma))
				.collect(Collectors.toList());
	}

	private static List<String> getIdiomasDistintos() {
		return Arrays.asList(idiomas).stream()
				.distinct()
				.collect(Collectors.toList());
	}

	private static String getQuantidadeDeFilmes(String idioma) {
		int quantidade = (int) Arrays.asList(idiomas).stream()
				.filter(i -> i.equalsIgnoreCase(idioma))
				.count();

		String corpo = "";

		if (quantidade == 1) {
			corpo += quantidade + " filme visto no idioma " + idioma;
		} else {
			corpo += quantidade + " filmes vistos no idioma " + idioma;
		}

		int porcentagem = Calculadora.calcularPorcentagem(idiomas.length, quantidade);
		corpo += " (aprox. " + porcentagem + "%)";

		return corpo;
	}

	public static boolean filtrarPorIdioma(Filme filme, String idioma) {
		return filme.getIdioma().getAbreviacao().startsWith(idioma);
	}
}