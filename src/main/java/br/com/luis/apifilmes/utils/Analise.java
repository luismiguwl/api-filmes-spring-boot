package br.com.luis.apifilmes.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.models.Filme;

public class Analise {
	public static List<String> getSomaDaDuracaoDeTodosOsFilmes(List<Filme> filmes) {
		List<String> informacoes = new ArrayList<>();
		
		int minutosAssistidos = filmes.stream()
				.map(filme -> filme.getRuntime())
				.reduce((total, duracao) -> total + duracao).get();
		
		int quantidadeDeHoras = definirQuantidadeDeHoras(minutosAssistidos);
		int quantidadeDeDias = definirQuantidadeDeDias(quantidadeDeHoras);
		
		informacoes.add("Minutos assistidos: " + minutosAssistidos);
		informacoes.add("Total em horas: " + quantidadeDeHoras);
		informacoes.add("Total em dias: " + quantidadeDeDias);
		
		return informacoes;
	}
	
	private static int definirQuantidadeDeHoras(int minutos) {
		return minutos / 60;
	}
	
	private static int definirQuantidadeDeDias(int horas) {
		return horas / 24;
	}
}
