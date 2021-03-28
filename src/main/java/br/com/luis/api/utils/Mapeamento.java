package br.com.luis.api.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.arquivo.Arquivo;
import br.com.luis.api.models.Diretor;
import br.com.luis.api.models.Filme;
import br.com.luis.api.models.Idioma;

public class Mapeamento {
	public static List<Filme> converterLinhaEmObjeto() {
		List<String> lista = Arquivo.lerArquivo();
		List<Filme> filmes = new ArrayList<>();
		lista.remove(0); // remove o cabeçalho do arquivo

		for (int i = 0; i < lista.size(); i++) {
			String linhaAtual = lista.get(i);
			String titulo = "";
			String dataAssistido = "";
			Idioma idioma = new Idioma();
			Diretor diretor = null;
			int anoLancamento = 0;
			List<Diretor> diretores = new ArrayList<>();

			Filme filme = new Filme(titulo, dataAssistido, anoLancamento, idioma, diretor);

			if (identificarTituloComVirgula(linhaAtual) || identificarMaisDeUmDiretor(linhaAtual)) {
				if (identificarTituloComVirgula(linhaAtual) && !identificarMaisDeUmDiretor(linhaAtual)) {
					titulo = isolarTituloComVirgula(linhaAtual);

					linhaAtual = linhaAtual.substring(linhaAtual.indexOf(titulo)).replace("\"", "");
					linhaAtual = linhaAtual.replace(titulo, "").substring(1);

					dataAssistido = linhaAtual.split(",")[0];
					anoLancamento = Integer.parseInt(linhaAtual.split(",")[1]);
					idioma = new Idioma(linhaAtual.split(",")[2]);
					diretor = new Diretor(linhaAtual.split(",")[3].trim());

					filme = new Filme(titulo, dataAssistido, anoLancamento, idioma, diretor);
				} else if (!identificarTituloComVirgula(linhaAtual) && identificarMaisDeUmDiretor(linhaAtual)) {
					diretores = mapearDiretores(linhaAtual);
					String posicaoDiretor = isolarQuandoHouverMaisDeUmDiretor(linhaAtual);
					linhaAtual = linhaAtual.replace(posicaoDiretor, "");
					linhaAtual = linhaAtual.substring(0, linhaAtual.length() - 1);

					titulo = linhaAtual.split(",")[0].trim();
					dataAssistido = linhaAtual.split(",")[1];
					anoLancamento = Integer.parseInt(linhaAtual.split(",")[2]);
					idioma = new Idioma(linhaAtual.split(",")[3]);

					filme = new Filme(titulo, dataAssistido, anoLancamento, idioma, diretores);
				}
			} else {
				titulo = linhaAtual.split(",")[0].trim();
				dataAssistido = linhaAtual.split(",")[1];
				anoLancamento = Integer.parseInt(linhaAtual.split(",")[2]);
				idioma = new Idioma(linhaAtual.split(",")[3]);
				diretor = new Diretor(linhaAtual.split(",")[4].trim());

				filme = new Filme(titulo, dataAssistido, anoLancamento, idioma, diretor);
			}

			filmes.add(filme);
		}

		return filmes;
	}

	private static boolean identificarTituloComVirgula(String linha) {
		return linha.charAt(0) == '\"';
	}

	private static String isolarTituloComVirgula(String linha) {
		return linha.substring(1, linha.indexOf("\"", 1));
	}

	private static String isolarQuandoHouverMaisDeUmDiretor(String linha) {
		return linha.substring(linha.indexOf("\""));
	}

	private static boolean identificarMaisDeUmDiretor(String linha) {
		return !identificarTituloComVirgula(linha) && linha.contains("\"");
	}

	private static List<Diretor> mapearDiretores(String linhaAtual) {
		List<Diretor> diretores = new ArrayList<>();

		String linhaDiretores = isolarQuandoHouverMaisDeUmDiretor(linhaAtual);
		linhaDiretores = removerAspas(linhaDiretores);
		String[] conteudo = linhaDiretores.split(",");
		diretores = Arrays.asList(conteudo).stream().map(diretor -> new Diretor(diretor.trim())).collect(Collectors.toList());
		return diretores;
	}

	private static String removerAspas(String linha) {
		return linha.replace("\"", "");
	}
}
