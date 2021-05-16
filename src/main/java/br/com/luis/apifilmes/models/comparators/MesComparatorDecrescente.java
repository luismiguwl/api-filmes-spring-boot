package br.com.luis.apifilmes.models.comparators;

import java.util.Comparator;

import br.com.luis.apifilmes.models.Mes;

public class MesComparatorDecrescente implements Comparator<Mes> {
	@Override
	public int compare(Mes mes1, Mes mes2) {
		return Integer.compare(mes2.getQuantidadeDeFilmesVistosNoMes(),
				mes1.getQuantidadeDeFilmesVistosNoMes());
	}
}
