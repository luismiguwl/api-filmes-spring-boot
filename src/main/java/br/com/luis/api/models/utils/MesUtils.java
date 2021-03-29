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
		}
		
		return null;
	}
	
	public static Mes definirDadosDoMes(Filme filme) {
		return new Mes(definirNomeDoMes(filme), definirNumeroDoMes(filme));
	}
}