package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.TipoDeConsulta;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

public class IdiomaUtils {
	private static List<Filme> filmes = Mapeamento.getFilmes(TipoDeConsulta.VISTOS);
	
	public static String getAbreviacao(String idioma) {
		List<String> listaDeAbreviacoes = Mapeamento.getAbreviacoes();
		return listaDeAbreviacoes.stream()
				.filter(abreviacao -> abreviacao.split(",")[0].equals(idioma))
				.map(abreviacao -> abreviacao.split(",")[1]).collect(Collectors.toList())
				.get(0).toLowerCase();
	}

	private static String getQuantidadeDeFilmes(String idioma) {
		String corpo = "";

		int quantidade = (int) filmes.stream().filter(filme -> filme.getIdioma().getNome().equalsIgnoreCase(idioma))
				.count();
		if (quantidade == 1) {
			corpo += quantidade + " filme visto em " + idioma;
		} else {
			corpo += quantidade + " filmes vistos em " + idioma;
		}

		corpo += " (aprox. " + Calculadora.calcularPorcentagem(filmes.size(), quantidade) + "%)";

		return corpo;
	}

	private static List<String> getIdiomasDistintos() {
		return filmes.stream().map(filme -> filme.getIdioma().getNome()).distinct().collect(Collectors.toList());
	}

	public static List<String> definirQuantidadeDeFilmesEmDeterminadoIdioma() {
		return getIdiomasDistintos().stream().map(filme -> getQuantidadeDeFilmes(filme)).collect(Collectors.toList());
	}
}