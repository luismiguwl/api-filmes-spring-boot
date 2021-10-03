package br.com.luis.apifilmes.models.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Genero;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;
import lombok.Getter;

@Getter
public class GeneroUtils {
	public static String[] generos = Mapeamento.getDadosDaColuna(Coluna.GENERO);

	public static List<String> getTodosOsGeneros() {
		List<String> todosOsGeneros = obterListaDeGenerosDistintos(generos);
		List<String> quantidadeDeFilmesEmCadaGenero = new ArrayList<>();
		List<String> generosDistintos = todosOsGeneros.stream().distinct().collect(Collectors.toList());

		for (int i = 0; i < generosDistintos.size(); i++) {
			String quantidade = calcularQuantidadeDeCadaGenero(generosDistintos.get(i), todosOsGeneros);
			quantidadeDeFilmesEmCadaGenero.add(quantidade);
		}

		return quantidadeDeFilmesEmCadaGenero;
	}

	public static List<String> obterListaDeGenerosDistintos(String[] listaDeGenerosSeparadoPorVirgula) {
		List<String> generos = new ArrayList<>();

		for (String linhaDeGenerosSeparadaPorVirgula : listaDeGenerosSeparadoPorVirgula) {
			String[] generosContidosNaLinha = linhaDeGenerosSeparadaPorVirgula.split(",");

			for (String genero : generosContidosNaLinha) {
				generos.add(genero.trim());
			}
		}

		return generos;
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

	public static int getQuantidadeDeFilmes(Genero generoAlvo) {
		List<Genero> generos = (List<Genero>) MapeamentoUtils.obterListaDeObjetosBaseadoNaString(Genero.class, generoAlvo.getNome());

		return (int) generos.stream()
				.filter(genero -> genero.getNome().equals(generoAlvo.getNome()))
				.count();
	}

	public static List<Genero> getAllGenerosDistintos(List<Filme> filmes) {
		List<Genero> allGeneros = new ArrayList<>();

		for (Filme filme : filmes) {
			List<Genero> generos = filme.getGeneros();
			allGeneros.addAll(generos);
		}
		
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