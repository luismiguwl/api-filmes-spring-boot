package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Genero;

public class MapeamentoUtils {
	public static List<Diretor> obterListaDeDiretoresBaseadoNumaListaDeLinhasContendoNomes(String... linhas) {
		List<Diretor> diretores = new ArrayList<>();

		for (String linha : linhas) {
			if (linha.contains(",")) {
				String[] elementosDaLinhaSeparadaPorVirgula = linha.split(",");

				for (String nome : elementosDaLinhaSeparadaPorVirgula) {
					diretores.add(new Diretor(nome.trim()));
				}
			} else {
				diretores.add(new Diretor(linha.trim()));
			}
		}

		return diretores;
    }
	
	public static List<Genero> obterListaContendoCadaGeneroBaseadoNumaString(String... linhas) {
		List<Genero> generos = new ArrayList<>();

		for (String linha : linhas) {
			if (linha.contains(",")) {
				String[] elementosDaLinhaSeparadaPorVirgula = linha.split(",");

				for (String nome : elementosDaLinhaSeparadaPorVirgula) {
					generos.add(new Genero(nome.trim()));
				}
			} else {
				generos.add(new Genero(linha.trim()));
			}
		}

		return generos;
	}

	public static String[] converterListaDeStringParaArray(List<String> strings) {
		return strings.toArray(new String[0]);
	}
}
