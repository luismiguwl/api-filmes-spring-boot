package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.utils.Mapeamento;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream()
				.map(diretor -> diretor.getNome())
				.collect(Collectors.joining(" "));
	}

	public static int getQuantidadeDeFilmesVistos(Diretor diretor) {
		String[] nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = (List<Diretor>) MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Diretor.class, nomeDosDiretores);
		return (int) diretores.stream()
				.filter(d -> d.getNome().equals(diretor.getNome()))
				.count();
	}

	public static List<Diretor> filtrarDiretoresComMaisFilmes(List<Filme> filmes, int top) {
		String[] nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = (List<Diretor>) MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Diretor.class, nomeDosDiretores);

		diretores = getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(diretores);

		return diretores.stream()
				.distinct()
				.limit(top)
				.collect(Collectors.toList());
	}

	public static List<Diretor> getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(
			List<Diretor> diretores) {
		return diretores.stream()
				.sorted(Comparator.comparing(Diretor::getQuantidadeDeFilmesVistos).reversed())
				.collect(Collectors.toList());
	}

	public static List<Diretor> getAllDiretoresDistintos(List<Filme> filmes) {
		List<Diretor> allDiretores = new ArrayList<>();

		for (Filme filme : filmes) {
			List<Diretor> diretoresDoFilmeAtual = filme.getDiretores();
			allDiretores.addAll(diretoresDoFilmeAtual);
		}

		return allDiretores.stream()
				.distinct()
				.collect(Collectors.toList());
	}

}
