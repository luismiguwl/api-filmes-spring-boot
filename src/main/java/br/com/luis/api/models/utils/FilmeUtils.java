package br.com.luis.api.models.utils;

import java.util.List;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.Mes;
import br.com.luis.api.utils.Mapeamento;

public class FilmeUtils {
	public static int mapearQuantidadeDeFilmesVistosNoMes(Mes mes) {
		List<Filme> filmes = Mapeamento.converterLinhaEmObjeto();
		return 0;
	}
	
	public static Mes definirEmQueMesFoiVisto(Filme filme) {
		String[] dadosDaData = filme.getData().split("/");
		int mesEmFoiAssistido = Integer.parseInt(dadosDaData[1]);
		return Mes.values()[mesEmFoiAssistido - 1];
	}
}