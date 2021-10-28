package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapeamentoUtils {
	public static <T> List<T> obterListaDeObjetosBaseadoNaString(Function<String, T> fn, String ... linhas) {
		List<String> nomes = new ArrayList<>();
		
		for (String linha : linhas) {
			if (linha.contains(",")) {
				String[] elementosDaLinhaSeparadaPorVirgula = linha.split(",");

				for (String nome : elementosDaLinhaSeparadaPorVirgula) {
					nomes.add(nome.trim());
				}
			} else {
				nomes.add(linha.trim());
			}
		}
		
		return nomes.stream()
				.map(fn)
				.collect(Collectors.toList());
	}

	public static String[] converterListaDeStringParaArray(List<String> strings) {
		return strings.toArray(new String[0]);
	}
}
