package br.com.luis.apifilmes.models.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Genero;

public class GeneroUtils {
	public static List<Genero> getListaDeGenerosOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(List<Genero> generos) {
		return generos.stream()
				.sorted(Comparator.comparing(Genero::getQuantidadeDeFilmes).reversed())
				.collect(Collectors.toList());
	}
}