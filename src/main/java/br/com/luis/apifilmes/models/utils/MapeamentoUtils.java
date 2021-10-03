package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Genero;

public class MapeamentoUtils {
	public static List<?> obterListaDeObjetosBaseadoNaString(Class classe,
			String... linhas) {
		List<String> nomes = new ArrayList<>();
		
		for (String linha : linhas) {
			if (linha.contains(",")) {
				String[] elementosDaLinhaSeparadaPorVirgula = linha.split(",");

				for (String nome : elementosDaLinhaSeparadaPorVirgula) {
					nomes.add(nome);
				}
			} else {
				nomes.add(linha);
			}
		}
		
		if (classe == Diretor.class) {
			return nomes.stream()
					.map(nome -> new Diretor(nome))
					.collect(Collectors.toList());
		} else {
			return nomes.stream()
					.map(nome -> new Genero(nome))
					.collect(Collectors.toList());
		}

	}

	public static String[] converterListaDeStringParaArray(List<String> strings) {
		return strings.toArray(new String[0]);
	}
}
