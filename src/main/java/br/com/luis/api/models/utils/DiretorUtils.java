package br.com.luis.api.models.utils;

import java.util.List;

import br.com.luis.api.models.Diretor;
import br.com.luis.api.utils.Extrator;

public class DiretorUtils {
	public static int definirQuantidadeDeFilmesDirigidos(String nome) {
		List<Diretor> diretores = Extrator.extrairTodosOsDiretores();
		return (int) diretores.stream()
				.filter(diretor -> diretor.getNome().equals(nome))
				.count();
	}
}
