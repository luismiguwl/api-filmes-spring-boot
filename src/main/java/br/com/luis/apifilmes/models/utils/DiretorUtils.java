package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.enums.Destino;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream()
				.map(diretor -> diretor.getNome())
				.collect(Collectors.joining(" "));
	}

	public static int getQuantidadeDeFilmesVistos(String[] nomeDosDiretores, Diretor diretorAlvo) {
		List<Diretor> diretores = MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Diretor::new, nomeDosDiretores);
		
		return (int) diretores.stream()
				.filter(diretor -> diretor.getNome().equals(diretorAlvo.getNome()))
				.count();
	}

	public static List<Diretor> filtrarDiretoresComMaisFilmes(String[] nomeDosDiretores, int top) {
		List<Diretor> diretores = MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Diretor::new, nomeDosDiretores);
		diretores = getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(diretores);
		List<Diretor> rankingDeDiretoresComMaisFilmes = new ArrayList<>();
		
		for (Diretor diretor : diretores) {
			boolean onList = 
					rankingDeDiretoresComMaisFilmes.stream()
					.anyMatch(diretorRanking -> diretorRanking.getNome().equals(diretor.getNome()));
			
			if (!onList) {
				rankingDeDiretoresComMaisFilmes.add(diretor);
			}
		}
		
		return rankingDeDiretoresComMaisFilmes.stream()
				.limit(top)
				.distinct()
				.collect(Collectors.toList());
	}

	public static List<Diretor> getListaDeDiretoresOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(
			List<Diretor> diretores) {
		return diretores.stream()
				.filter(diretor -> diretor.getQuantidadeDeFilmesVistos() != null)
				.sorted(Comparator.comparing(Diretor::getQuantidadeDeFilmesVistos).reversed())
				.collect(Collectors.toList());
	}

}
