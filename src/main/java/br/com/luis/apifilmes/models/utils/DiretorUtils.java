package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Coluna;
import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.utils.Mapeamento;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream().map(diretor -> diretor.getNome() + " ").collect(Collectors.joining()).trim();
	}

	public static int getQuantidadeDeFilmesVistos(Diretor diretor) {
		List<String> nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = mapearDiretores(nomeDosDiretores);
		return (int) diretores.stream().filter(d -> d.getNome().equals(diretor.getNome())).count();
	}

	public static List<Diretor> mapearDiretores(List<String> dados) {
		List<Diretor> diretores = new ArrayList<>();
		dados.stream().forEach(linha -> diretores.addAll(MapeamentoUtils.mapearDiretores(linha)));
		return diretores;
	}

	public static List<Diretor> filtrarDiretoresComMaisFilmes(List<Filme> filmes, int top) {
		List<String> nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = mapearDiretores(nomeDosDiretores);

		diretores = getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(diretores);

		return diretores.stream().distinct().limit(top).collect(Collectors.toList());
	}

	public static List<Diretor> getAllDiretores(List<Filme> filmes) {
		List<Diretor> allDiretores = new ArrayList<>();

		filmes.forEach(filme -> {
			allDiretores.addAll(filme.getDiretores());
			allDiretores.add(filme.getDiretor());
		});

		return allDiretores.stream().filter(diretor -> diretor != null).distinct().collect(Collectors.toList());
	}

	public static List<Diretor> getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(
			List<Diretor> diretores) {
		diretores = diretores.stream()
				.sorted(Comparator.comparing(Diretor::getQuantidadeDeFilmesVistos).reversed())
				.collect(Collectors.toList());

		return diretores;
	}
}
