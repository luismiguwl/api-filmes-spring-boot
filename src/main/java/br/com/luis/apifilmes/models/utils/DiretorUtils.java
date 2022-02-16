package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream()
				.map(diretor -> diretor.getNome())
				.collect(Collectors.joining(" "));
	}

	public static List<Diretor> filtrarDiretoresComMaisFilmes(List<Diretor> diretores, int top) {
		if (top < 1) {
			return List.of();
		}
		
		diretores = ordenarDecrescentePorQuantidadeDeFilmes(diretores);
		
		if (top > 1) {
			return diretores.stream()
					.limit(top)
					.distinct()
					.collect(Collectors.toList());
		}
		
		return List.of(diretores.get(0));
	}

	public static List<Diretor> ordenarDecrescentePorQuantidadeDeFilmes(
			List<Diretor> diretores) {
		Predicate<Diretor> quantidadeDeFilmesNaoNula = diretor -> {
			return diretor.getQuantidadeDeFilmesVistos() != null;
		};
		
		return diretores.stream()
				.filter(quantidadeDeFilmesNaoNula)
				.sorted(Comparator.comparing(Diretor::getQuantidadeDeFilmesVistos).reversed())
				.collect(Collectors.toList());
	}

}
