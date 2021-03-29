package br.com.luis.api.models.utils;

import br.com.luis.api.models.Filme;
import br.com.luis.api.models.Mes;

public class MesUtils {


	private static int definirNumeroDoMes(Filme filme) {
		String[] data = filme.getData().split("/");
		return Integer.parseInt(data[1]);
	}

	private static String definirNomeDoMes(Filme filme) {
		switch (definirNumeroDoMes(filme)) {
		    case 1:
			    return "Janeiro";
		    case 2:
		    	return "Fevereiro";
		    case 3:
		    	return "Março";
		    case 4:
		    	return "Abril";
		    case 5:
		    	return "Maio";
		    case 6:
		    	return "Junho";
			case 7:
				return "Julho";
			case 8:
				return "Agosto";
			case 9:
				return "Setembro";
			case 10:
				return "Outubro";
			case 11:
				return "Novembro";
			case 12:
				return "Dezembro";
		}
		
		return null;
	}
	
	public static Mes definirDadosDoMes(Filme filme) {
		return new Mes(definirNomeDoMes(filme), definirNumeroDoMes(filme));
	}
}