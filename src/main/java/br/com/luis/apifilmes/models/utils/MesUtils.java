package br.com.luis.apifilmes.models.utils;

import static br.com.luis.apifilmes.models.utils.FilmeUtils.verificarSeFilmeFoiVistoNoMes;
import static br.com.luis.apifilmes.utils.Calculadora.calcularPorcentagem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Mes;
import br.com.luis.apifilmes.models.TipoDeConsulta;
import br.com.luis.apifilmes.utils.Mapeamento;

@JsonInclude(Include.NON_EMPTY)
public class MesUtils {
	private static List<Filme> filmes = Mapeamento.getFilmes(TipoDeConsulta.VISTOS);
	private static String[] nomeDosMeses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
			"Setembro", "Outubro", "Novembro", "Dezembro" };

	private static int definirNumeroDoMes(String data) {
		String[] dadosDaData = data.split("/");
		return Integer.parseInt(dadosDaData[1]);
	}

	private static String definirNomeDoMes(int numeroDoMes) {
		return nomeDosMeses[numeroDoMes - 1];
	}

	public static Mes definirDadosDoMes(Filme filme) {
		if (filme.getDataEmQueFoiAssistido() != null) {
			int numeroDoMes = definirNumeroDoMes(filme.getDataEmQueFoiAssistido());
			String nomeDoMes = definirNomeDoMes(numeroDoMes);
			return new Mes(nomeDoMes, numeroDoMes);
		}
		
		return null;
	}

	public static List<String> listarQuantidadeDeCadaMes() {
		return filmes.stream()
				.map(filme -> getQuantidadeDeCadaMes(filme.getMes())).distinct()
				.collect(Collectors.toList());
	}

	private static String getQuantidadeDeCadaMes(Mes mes) {
		int quantidade = (int) filmes.stream()
				.filter(filme -> verificarSeFilmeFoiVistoNoMes(filme, mes))
				.count();
		
		int porcentagem = calcularPorcentagem(filmes.size(), quantidade);
 
		if (quantidade == 1) {
			return quantidade + " filme visto em " + mes.getNome() + " (aprox. " + porcentagem + "% do total)";
		}

		return quantidade + " filmes vistos em " + mes.getNome() + " (aprox. " + porcentagem + "% do total)";
	}

	public static boolean filtrarPorMes(Filme filme, int mes) {
		return filme.getMes().getNumeroDoMes() == mes;
	}
	
	public static List<Mes> getListaDeMesesOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(List<Mes> meses) {
		Collections.sort(meses, new Comparator<Mes>() {
			public int compare(Mes o1, Mes o2) {
				return Integer.compare(o2.getQuantidadeDeFilmesVistosNoMes(), o1.getQuantidadeDeFilmesVistosNoMes());
			}
		});
		
		return meses;
	}

}