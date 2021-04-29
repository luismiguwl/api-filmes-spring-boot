package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Coluna;
import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.comparators.DiretorComparatorDecrescente;
import br.com.luis.apifilmes.utils.Mapeamento;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream()
				.map(diretor -> diretor.getNome() + " ")
				.collect(Collectors.joining()).trim();
	}

	public static int getQuantidadeDeFilmesVistos(Diretor diretor) {
		List<String> nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = mapearDiretores(nomeDosDiretores);
		return (int) diretores.stream()
				.filter(d -> d.getNome().equals(diretor.getNome()))
				.count();
	}
	
	private static List<Diretor> mapearDiretores(List<String> dados) {
		List<Diretor> diretores = new ArrayList<>();
		dados.stream().forEach(linha -> diretores.addAll(MapeamentoUtils.mapearDiretores(linha)));
		return diretores;
	}
	
	public static List<String> filtrarDiretoresComMaisFilmes(List<Filme> filmes, int top) {
		List<String> nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = mapearDiretores(nomeDosDiretores);
		
		DiretorComparatorDecrescente diretorComparatorDecrescente = new DiretorComparatorDecrescente();
		Collections.sort(diretores, diretorComparatorDecrescente);
		
		return diretores.stream()
				.map(diretor -> diretor.getNome() + " - " + diretor.getQuantidadeDeFilmesVistos() + " filmes")
				.distinct()
				.limit(top)
				.collect(Collectors.toList());
	}
	
}
