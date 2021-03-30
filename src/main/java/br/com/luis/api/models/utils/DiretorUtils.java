package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.models.Diretor;
import br.com.luis.api.utils.Extrator;

public class DiretorUtils {
	public static int definirQuantidadeDeFilmesDirigidos(String nome) {
		List<Diretor> diretores = Extrator.extrairTodosOsDiretores();
		return (int) diretores.stream().filter(diretor -> diretor.getNome().equals(nome)).count();
	}

	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream()
				.map(diretor -> diretor.getNome() + " ")
				.collect(Collectors.joining());
	}
}
