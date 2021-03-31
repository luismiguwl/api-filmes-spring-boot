package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.arquivo.Arquivo;
import br.com.luis.api.models.Filme;
import br.com.luis.api.models.TipoDeConsulta;
import br.com.luis.api.utils.Calculadora;
import br.com.luis.api.utils.Mapeamento;

public class IdiomaUtils {
	private static List<Filme> filmes = Mapeamento.getFilmesVistos();

	public static String getAbreviacao(String idioma) {
		List<String> listaDeAbreviacoes = Arquivo.lerArquivo(TipoDeConsulta.ABREVIACOES);

		return listaDeAbreviacoes.stream().filter(abreviacao -> abreviacao.split(",")[0].equals(idioma))
				.map(abreviacao -> abreviacao.split(",")[1]).collect(Collectors.toList()).get(0);
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