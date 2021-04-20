package br.com.luis.apifilmes.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.TipoDeConsulta;

public class Extrator {
	public static List<String> extrairNomeDeTodosDiretores(TipoDeConsulta tipo) {
		List<Filme> filmes = Mapeamento.getFilmes(tipo);
		List<String> nomeDosDiretores = new ArrayList<>();

		for (int i = 0; i < filmes.size(); i++) {
			if (!filmes.get(i).getDiretores().isEmpty()) {
				nomeDosDiretores.addAll(obterNomeDosDiretoresDoFilme(filmes.get(i)));
				continue;
			}

			nomeDosDiretores.add(filmes.get(i).getDiretor().getNome());
		}

		return nomeDosDiretores;
	}

	private static List<String> obterNomeDosDiretoresDoFilme(Filme filme) {
		return filme.getDiretores().stream().map(diretor -> diretor.getNome()).collect(Collectors.toList());
	}
}
