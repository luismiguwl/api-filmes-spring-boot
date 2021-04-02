package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.Mes;
import br.com.luis.api.utils.Calculadora;
import br.com.luis.api.utils.Mapeamento;

@JsonInclude(Include.NON_EMPTY)
public class MesUtils {
	private static List<Filme> filmes = Mapeamento.getFilmesVistos();

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
		// caso o filme esteja na lista de pendentes, ele não possui mês em que foi
		// assistido
		// portanto, não precisa ser mapeado para o corpo da resposta
		if (filme.getData() != null) {
			return new Mes(definirNomeDoMes(filme), definirNumeroDoMes(filme));
		}

		return null;
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