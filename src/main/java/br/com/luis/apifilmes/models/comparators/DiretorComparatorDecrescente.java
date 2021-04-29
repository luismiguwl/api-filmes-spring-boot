package br.com.luis.apifilmes.models.comparators;

import java.util.Comparator;

import br.com.luis.apifilmes.models.Diretor;

public class DiretorComparatorDecrescente implements Comparator<Diretor> {
	
	@Override
	public int compare(Diretor firstDirector, Diretor secondDirector) {
		return Integer.compare(secondDirector.getQuantidadeDeFilmesVistos(), firstDirector.getQuantidadeDeFilmesVistos());
	}
}
