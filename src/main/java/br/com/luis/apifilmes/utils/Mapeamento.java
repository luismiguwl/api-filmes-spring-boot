package br.com.luis.apifilmes.utils;

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

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Idioma;
import br.com.luis.apifilmes.models.TipoDeConsulta;

public class Mapeamento {
	public static List<Filme> getFilmes(TipoDeConsulta tipo) {
		System.out.println("chamou o getFilmes()");
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
				int runtime = Integer.parseInt(csv.get("duracao"));
				Idioma idioma = new Idioma(csv.get("idioma"));

				if (csv.get("diretor").contains(",")) {
					diretores = mapearDiretores(csv.get("diretor"));
				} else {
					diretor = new Diretor(csv.get("diretor"));
				}

				genero = csv.get("genero");

				filme = new Filme(titulo, ano, data, idioma, diretor, genero, diretores, runtime);
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

	public static List<String> getAbreviacoes() {
		List<String> abreviacoes = new ArrayList<>();
		String destino = TipoDeConsulta.ABREVIACOES.getDestino(TipoDeConsulta.ABREVIACOES);

		CsvReader csv;

		try {
			csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
			csv.readHeaders();

			while (csv.readRecord()) {
				abreviacoes.add(csv.get("idioma") + "," + csv.get("abreviacao"));
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		return abreviacoes;
	}
}
