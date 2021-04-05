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
	public static List<Filme> getFilmesVistos() {
		TipoDeConsulta tipo = TipoDeConsulta.VISTOS;
		List<Filme> filmes = new ArrayList<>();
		String destino = tipo.getDestino(tipo);
		CsvReader csv;

		Filme filme;

		try {
			csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));

			csv.readHeaders();

			while (csv.readRecord()) {
				List<Diretor> diretores = new ArrayList<>();
				List<String> generos = new ArrayList<>();

				Diretor diretor = new Diretor();
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

				filme = new Filme(titulo, data, idioma, diretor, genero, ano, diretores);
				filmes.add(filme);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		return filmes;
	}

	public static List<Filme> getFilmesPendentes() {
		TipoDeConsulta tipo = TipoDeConsulta.PENDENTES;
		List<Filme> filmes = new ArrayList<>();
		String destino = tipo.getDestino(tipo);
		CsvReader csv;

		Filme filme;

		try {
			csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));

			csv.readHeaders();

			while (csv.readRecord()) {
				List<Diretor> diretores = new ArrayList<>();

				Diretor diretor = new Diretor();
				String genero;

				String titulo = csv.get("titulo");
				int ano = Integer.parseInt(csv.get("anoDeLancamento"));
				Idioma idioma = new Idioma(csv.get("idioma"));

				if (csv.get("diretor").contains(",")) {
					diretores = mapearDiretores(csv.get("diretor"));
				} else {
					diretor = new Diretor(csv.get("diretor"));
				}

				genero = csv.get("genero");

				filme = new Filme(titulo, idioma, diretor, genero, ano, diretores);
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
