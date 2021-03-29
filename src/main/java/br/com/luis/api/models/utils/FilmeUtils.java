package br.com.luis.api.models.utils;

import br.com.luis.api.models.Filme;

public class FilmeUtils {
	public static String listarDiretores(Filme filme) {
		if (!filme.getDiretores().isEmpty()) {
			String corpo = "Diretores: ";

			for (int i = 0; i < filme.getDiretores().size(); i++) {
				corpo += "" + filme.getDiretores().get(i).getNome();
				if (i != (filme.getDiretores().size() - 1)) {
					corpo += ", ";
				}
			}

			return corpo;
		}
		return "Diretor: " + filme.getDiretor().getNome();
	}
}