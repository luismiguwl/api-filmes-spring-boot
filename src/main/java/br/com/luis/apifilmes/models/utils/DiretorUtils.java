package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.utils.Mapeamento;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream().map(diretor -> diretor.getNome() + " ").collect(Collectors.joining());
	}

	public static int getQuantidadeDeFilmesVistos(Diretor diretor) {
		List<String> nomeDosDiretores = Mapeamento.getDadosDaColuna("diretor");
		List<Diretor> diretores = mapearDiretores(nomeDosDiretores);
		return (int) diretores.stream()
				.filter(d -> d.getNome().equals(diretor.getNome()))
				.count();
	}
	
	private static List<Diretor> mapearDiretores(List<String> dados) {
		List<Diretor> diretores = new ArrayList<>();
		dados.stream().forEach(linha -> diretores.addAll(Mapeamento.mapearDiretores(linha)));
		return diretores;
	}
}
