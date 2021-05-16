package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Coluna;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Genero;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;
import lombok.Getter;

@Getter
public class GeneroUtils {
	public static List<String> generos = Mapeamento.getDadosDaColuna(Coluna.GENERO);

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

	public static List<String> obterCadaGenero(List<String> generos) {
		List<String> generosDistintos = new ArrayList<>();

		for (int i = 0; i < generos.size(); i++) {
			String[] gens = generos.get(i).split(",");
			Arrays.stream(gens).forEach(g -> generosDistintos.add(g.trim()));
		}

		return generosDistintos;
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

	public static List<Genero> getAllGeneros(List<Filme> filmes) {
		List<Genero> allGeneros = new ArrayList<>();
		
		filmes.forEach(filme -> {
			if (!filme.getGeneros().isEmpty()) {
				allGeneros.addAll(filme.getGeneros());
			}
			
			if (filme.getGenero() != null) {
				allGeneros.add(filme.getGenero());
			}
		});
		
		return allGeneros.stream()
				.distinct()
				.collect(Collectors.toList());
	}
	
	public static List<Genero> getListaDeGenerosOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(List<Genero> generos) {
		Collections.sort(generos, new Comparator<Genero>() {
			public int compare(Genero o1, Genero o2) {
				return Integer.compare(o2.getQuantidadeDeFilmes(), o1.getQuantidadeDeFilmes());
			}
		});
		
		return generos;
	}
}