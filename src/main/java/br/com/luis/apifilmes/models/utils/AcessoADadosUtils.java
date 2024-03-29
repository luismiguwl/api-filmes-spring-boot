package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AcessoADadosUtils {
	public static <T> List<T> converterStringParaObjeto(Function<String, T> fn, String ... linhas) {
		List<String> nomes = new ArrayList<>();
		
		for (String linha : linhas) {
			for (String elemento : linha.split(",")) {
				if (!elemento.trim().isEmpty()) nomes.add(elemento.trim());
			}
		}
		
		return nomes.stream()
				.map(fn)
				.collect(Collectors.toList());
	}

	public static <T> String[] obterArrayContendoDadosDoAtributoDeUmaClasse(Function<T, String> fn, List<T> lista) {
		List<String> strings = new ArrayList<>();

		lista.stream()
			.map(fn)
			.forEach(elemento -> strings.add(elemento));
		
		return converterListaDeStringParaArray(strings);
	}
	
	public static String[] converterListaDeStringParaArray(List<String> strings) {
		return strings.toArray(new String[0]);
	}
}
