package br.com.luis.apifilmes.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.models.TipoDeConsulta;

public class ApiApplicationUtils {
	public static List<Integer> getQuantidadeDeFilmesAtualizada() {
		List<Integer> valores = new ArrayList<>();

		int quantidadeNaListaDeFilmesVistos = Mapeamento.getFilmes(TipoDeConsulta.VISTOS).size();
		int quantidadeNaListaDeFilmesPendentes = Mapeamento.getFilmes(TipoDeConsulta.PENDENTES).size();

		valores.add(quantidadeNaListaDeFilmesVistos);
		valores.add(quantidadeNaListaDeFilmesPendentes);

		return valores;
	}
	
	public static boolean identificarAtualizacaoEmAlgumaDasListas(List<Integer> quantidadeDeFilmesNasListas) {
		List<Integer> listaDeFilmesAtualizada = getQuantidadeDeFilmesAtualizada();
		return !listaDeFilmesAtualizada.containsAll(quantidadeDeFilmesNasListas);
	}
	
}
