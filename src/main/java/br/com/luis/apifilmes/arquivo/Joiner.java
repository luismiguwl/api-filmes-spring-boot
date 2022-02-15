package br.com.luis.apifilmes.arquivo;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Joiner {
	public <T> String getDadosSeparadosPorVirgulaSeNecessario(List<T> lista, Function<T, String> funcao) {
		if (lista.size() > 1) {
			return lista.stream()
					.map(funcao)
					.collect(Collectors.joining(", "));
		}
		
		return funcao.apply(lista.get(0));
	}
}
