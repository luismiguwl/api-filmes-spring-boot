package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Coluna;
import br.com.luis.apifilmes.models.Genero;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

public class GeneroUtils {
	private static List<String> generos = Mapeamento.getDadosDaColuna(Coluna.GENERO);

	public static List<String> getTodosOsGeneros() {
		List<String> todosOsGeneros = obterCadaGenero(generos);
		List<String> quantidadeDeFilmesEmCadaGenero = new ArrayList<>();
		List<String> generosDistintos = todosOsGeneros.stream().distinct().collect(Collectors.toList());

		for (int i = 0; i < generosDistintos.size(); i++) {
			String quantidade = calcularQuantidadeDeCadaGenero(generosDistintos.get(i), todosOsGeneros);
			quantidadeDeFilmesEmCadaGenero.add(quantidade);
		}

		return quantidadeDeFilmesEmCadaGenero;
	}

	private static List<String> obterCadaGenero(List<String> generos) {
		List<String> generosDistintos = new ArrayList<>();

		for (int i = 0; i < generos.size(); i++) {
			String[] gens = generos.get(i).split(",");
			Arrays.stream(gens).forEach(g -> generosDistintos.add(g.trim()));
		}

		return generosDistintos.stream().collect(Collectors.toList());
	}

	private static String calcularQuantidadeDeCadaGenero(String genero, List<String> generos) {
		int quantidade = (int) generos.stream().filter(gen -> gen.equals(genero)).count();
		int porcentagem = Calculadora.calcularPorcentagem(generos.size(), quantidade);

		if (quantidade > 1) {
			return quantidade + " filmes contém o gênero " + genero + " (aprox. " + porcentagem + "%)";
		} else {
			return quantidade + " filme contém o gênero " + genero + " (aprox. " + porcentagem + "%)";
		}
	}

	public static int getQuantidadeDeFilmes(Genero genero) {
		List<Genero> allGeneros = new ArrayList<>();
		generos.forEach(g -> allGeneros.addAll(MapeamentoUtils.mapearGeneros(g)));
		
		int quantidade = (int) allGeneros.stream()
				.filter(g -> g.getNome().equals(genero.getNome()))
				.count();
		
		return quantidade;
	}

}