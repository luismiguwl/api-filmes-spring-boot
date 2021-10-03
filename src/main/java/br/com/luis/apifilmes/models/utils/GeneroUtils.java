package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.luis.apifilmes.utils.Mapeamento.*;
import static br.com.luis.apifilmes.utils.Calculadora.*;
import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.utils.Calculadora;

public class GeneroUtils {
	private static Calculadora calculadora;
	
	private static String[] generos = getDadosDaColuna(Coluna.GENERO);

	public static List<String> obterListaContendoCadaGenero() {
		List<String> todosOsGeneros = obterListaDeGenerosDistintos(generos);
		List<String> generosDistintos = todosOsGeneros.stream()
				.distinct()
				.collect(Collectors.toList());

		return generosDistintos.stream()
						.map(genero -> calcularQuantidadeDeFilmesEmCadaGenero(genero, todosOsGeneros))
						.collect(Collectors.toList());
		
	}

	public static List<String> obterListaDeGenerosDistintos(String[] listaDeGenerosSeparadoPorVirgula) {
		List<String> generos = new ArrayList<>();

		Arrays.asList(listaDeGenerosSeparadoPorVirgula).forEach(linha -> {
			String[] generosContidosNaLinha = linha.split(",");

			Arrays.asList(generosContidosNaLinha).forEach(genero -> {
				generos.add(genero);
			});
		});
		
		return generos;
	}

	private static String calcularQuantidadeDeFilmesEmCadaGenero(String genero, List<String> generos) {
		int quantidade = (int) generos.stream()
				.filter(gen -> gen.equals(genero))
				.count();
		int porcentagem = calculadora.calcularPorcentagem(quantidade, generos.size());

		if (quantidade > 1) {
			return quantidade + " filmes contém o gênero " + genero + " (aprox. " + porcentagem + "%)";
		} else {
			return quantidade + " filme contém o gênero " + genero + " (aprox. " + porcentagem + "%)";
		}
	}

	public static int getQuantidadeDeFilmes(Genero generoAlvo) {
		List<Genero> generos = (List<Genero>) obterListaDeObjetosBaseadoNaString(Genero.class, generoAlvo.getNome());

		return (int) generos.stream()
				.filter(genero -> genero.getNome().equals(generoAlvo.getNome()))
				.count();
	}

	public static List<Genero> getAllGenerosDistintos(List<Filme> filmes) {
		final List<String> allGeneros = new ArrayList<>();

		filmes.forEach(filme -> {
			filme.getGeneros().forEach(genero -> {
				allGeneros.add(genero.getNome().trim());
			});
		});
		
		return allGeneros.stream()
				.distinct()
				.sorted()
				.map(nome -> new Genero(nome))
				.collect(Collectors.toList());
	}
	
	public static List<Genero> getListaDeGenerosOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(List<Genero> generos) {
		return generos.stream()
				.sorted(Comparator.comparing(Genero::getQuantidadeDeFilmes).reversed())
				.collect(Collectors.toList());
	}
}