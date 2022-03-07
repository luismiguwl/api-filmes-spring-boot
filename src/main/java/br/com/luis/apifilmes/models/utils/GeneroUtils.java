package br.com.luis.apifilmes.models.utils;

import java.util.*;
import java.util.stream.*;

import br.com.luis.apifilmes.models.*;

public class GeneroUtils {
	public static List<Genero> ordenarPorQuantidadeDecrescenteDeFilmes(List<Genero> generos) {
		return generos.stream()
				.sorted(Comparator.comparing(Genero::getQuantidadeDeFilmes).reversed())
				.collect(Collectors.toList());
	}

}