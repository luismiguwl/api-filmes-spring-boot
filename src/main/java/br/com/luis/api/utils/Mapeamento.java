package br.com.luis.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.csvreader.CsvReader;

import br.com.luis.api.models.*;

public class Mapeamento {
	public static List<Filme> getFilmes(TipoDeConsulta tipo) {
		List<Filme> filmes = new ArrayList<>();
		String destino = tipo.getDestino(tipo);
		CsvReader csv;

		Filme filme;

		try {
			csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));

			csv.readHeaders();

			while (csv.readRecord()) {
				List<Diretor> diretores = new ArrayList<>();

				Diretor diretor = null;
				String genero;

				String titulo = csv.get("titulo");
				String data = csv.get("dataAssistido");
				int ano = Integer.parseInt(csv.get("anoDeLancamento"));
				Idioma idioma = new Idioma(csv.get("idioma"));

				if (csv.get("diretor").contains(",")) {
					diretores = mapearDiretores(csv.get("diretor"));
				} else {
					diretor = new Diretor(csv.get("diretor"));
				}

				genero = csv.get("genero");

				filme = new Filme(titulo, ano, data, idioma, diretor, genero, diretores);
				filmes.add(filme);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		return filmes;
	}

	private static List<Diretor> mapearDiretores(String linha) {
		return Arrays.stream(linha.split(",")).map(diretor -> new Diretor(diretor.trim())).collect(Collectors.toList());
	}
}
