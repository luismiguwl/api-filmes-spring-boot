package br.com.luis.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import br.com.luis.api.models.Diretor;
import br.com.luis.api.models.Filme;

public class Extrator {
	public static List<String> extrairTodosOsDiretores() {
		List<Filme> filmes = Mapeamento.getFilmesVistos();
		List<String> diretores = new ArrayList<>();

		for (int i = 0; i < filmes.size(); i++) {
			if (!filmes.get(i).getDiretores().isEmpty()) {
				diretores.addAll(obterNomeDosDiretoresDoFilme(filmes.get(i)));
				continue;
			}

			diretores.add(filmes.get(i).getDiretor().getNome());
		}

		return diretores;
	}

	private static List<String> obterNomeDosDiretoresDoFilme(Filme filme) {
		return filme.getDiretores().stream().map(diretor -> diretor.getNome()).collect(Collectors.toList());
	}
}
