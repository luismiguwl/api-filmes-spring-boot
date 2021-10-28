package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import static br.com.luis.apifilmes.models.enums.Coluna.*;
import br.com.luis.apifilmes.utils.Mapeamento;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream()
				.map(diretor -> diretor.getNome())
				.collect(Collectors.joining(" "));
	}

	public static int getQuantidadeDeFilmesVistos(Diretor diretorAlvo) {
		String[] nomeDosDiretores = Mapeamento.getDadosDaColuna(DIRETOR);
		List<Diretor> diretores = MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Diretor::new, nomeDosDiretores);
		return (int) diretores.stream()
				.filter(d -> d.getNome().equals(diretorAlvo.getNome()))
				.count();
	}

	public static List<Diretor> filtrarDiretoresComMaisFilmes(List<Filme> filmes, int top) {
		String[] nomeDosDiretores = Mapeamento.getDadosDaColuna(DIRETOR);
		List<Diretor> diretores = MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Diretor::new, nomeDosDiretores);
		diretores = getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(diretores);

		List<Diretor> lista = new ArrayList<>();
		
		for (Diretor diretor : diretores) {
			boolean onList = lista.stream().anyMatch(novo -> novo.getNome().equals(diretor.getNome()));
			
			if (!onList) {
				lista.add(diretor);
			}
		}
		
		return lista.stream()
				.limit(top)
				.distinct()
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

		filmes.forEach(filme -> {
			allDiretores.addAll(filme.getDiretores());
		});
		
		return allDiretores.stream()
				.distinct()
				.collect(Collectors.toList());
	}

}
