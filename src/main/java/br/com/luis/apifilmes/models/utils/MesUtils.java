package br.com.luis.apifilmes.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Mes;
import br.com.luis.apifilmes.models.TipoDeConsulta;
import br.com.luis.apifilmes.utils.Calculadora;
import br.com.luis.apifilmes.utils.Mapeamento;

@JsonInclude(Include.NON_EMPTY)
public class MesUtils {
	private static List<Filme> filmes = Mapeamento.getFilmes(TipoDeConsulta.VISTOS);

	private static int definirNumeroDoMes(Filme filme) {
		String[] data = filme.getData().split("/");
		return Integer.parseInt(data[1]);
	}

	private static String definirNomeDoMes(Filme filme) {
		String[] nomeDosMeses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
				"Setembro", "Outubro", "Novembro", "Dezembro" };
		return nomeDosMeses[definirNumeroDoMes(filme) - 1];
	}

	public static Mes definirDadosDoMes(Filme filme) {
		return filme.getData() != null ? new Mes(definirNomeDoMes(filme), definirNumeroDoMes(filme)) : null;
	}

	public static List<String> listarQuantidadeDeCadaMes() {
		return filmes.stream().map(filme -> quantidadeDeCadaMes(filme.getMes())).distinct()
				.collect(Collectors.toList());
	}

	private static String quantidadeDeCadaMes(Mes mes) {
		int quantidade = (int) filmes.stream().filter(filme -> filme.getMes().getNome().equals(mes.getNome())).count();
		int porcentagem = Calculadora.calcularPorcentagem(filmes.size(), quantidade);
 
		if (quantidade == 1) {
			return quantidade + " filme visto em " + mes.getNome() + " (aprox. " + porcentagem + "% do total)";
		}

		return quantidade + " filmes vistos em " + mes.getNome() + " (aprox. " + porcentagem + "% do total)";
	}

}