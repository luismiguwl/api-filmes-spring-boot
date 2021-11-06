package br.com.luis.apifilmes.models.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import br.com.luis.apifilmes.models.*;

public class GeneroUtils {
	public static int getQuantidadeDeFilmes(Genero generoAlvo) {
		List<Genero> generos = obterListaDeObjetosBaseadoNaString(Genero::new, generoAlvo.getNome());
		return (int) generos.stream()
				.filter(genero -> genero.getNome().equals(generoAlvo.getNome()))
				.count();
	}

	public static List<Genero> getListaDeGenerosOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(List<Genero> generos) {
		return generos.stream()
				.sorted(Comparator.comparing(Genero::getQuantidadeDeFilmes).reversed())
				.collect(Collectors.toList());
	}
}