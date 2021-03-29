package br.com.luis.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.models.Diretor;
import br.com.luis.api.models.Filme;

public class Extrator {
	public static List<Diretor> extrairTodosOsDiretores() {
		List<Filme> filmes = Mapeamento.getFilmesVistos();
		List<Diretor> diretores = new ArrayList<>();
		
		filmes.addAll(Mapeamento.getFilmesPendentes());
		
		for (int i = 0; i < filmes.size(); i++) {
			if (!filmes.get(i).getDiretores().isEmpty()) {
				diretores.addAll(extrairNomeDosDiretoresDeDeterminadoFilme(filmes.get(i)));
				continue;
			}
			diretores.add(new Diretor(filmes.get(i).getDiretor().getNome()));
		}
		
		return diretores.stream().distinct().collect(Collectors.toList());
	}
	
	private static List<Diretor> extrairNomeDosDiretoresDeDeterminadoFilme(Filme filme) {
		return filme.getDiretores().stream()
				.map(diretor -> new Diretor(diretor.getNome()))
				.collect(Collectors.toList());
	}
}
