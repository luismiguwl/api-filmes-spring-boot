package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.models.Diretor;
import br.com.luis.api.models.TipoDeConsulta;
import br.com.luis.api.utils.Extrator;

public class DiretorUtils {
	public static String mesclarTodosOsDiretores(List<Diretor> diretores) {
		return diretores.stream().map(diretor -> diretor.getNome() + " ").collect(Collectors.joining());
	}

	public static int getQuantidadeDeFilmesVistos(Diretor diretor) {
		List<String> diretores = Extrator.extrairNomeDeTodosDiretores(TipoDeConsulta.VISTOS);
		return (int) diretores.stream().filter(d -> d.equals(diretor.getNome())).count();
	}
}
