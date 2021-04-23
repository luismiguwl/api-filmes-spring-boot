package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.models.TipoDeConsulta.getDestino;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;

import br.com.luis.apifilmes.models.*;

public class Mapeamento {

	private static Logger logger = LoggerFactory.getLogger(Mapeamento.class);

	public static List<Filme> getFilmes(TipoDeConsulta tipo) {
		List<Filme> filmes = new ArrayList<>();
		String destino = getDestino(tipo);

		try {
			CsvReader csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
			csv.readHeaders();

			while (csv.readRecord()) {
				List<Diretor> diretores = new ArrayList<>();
				Diretor diretor = null;

				String titulo = csv.get("titulo");
				String data = csv.get("dataAssistido");
				int ano = Integer.parseInt(csv.get("anoDeLancamento"));
				Idioma idioma = new Idioma(csv.get("idioma"));

				if (csv.get("diretor").contains(",")) {
					diretores = mapearDiretores(csv.get("diretor"));
				} else {
					diretor = new Diretor(csv.get("diretor"));
				}

				String genero = csv.get("genero");
				int runtime = Integer.parseInt(csv.get("duracao"));

				Filme filme = new Filme(titulo, ano, data, idioma, genero, diretor, diretores, runtime);
				filmes.add(filme);
			}
		} catch (IOException e) {
			logger.error("Arquivo não encontrado!");
		}

		return filmes;
	}

	private static List<Diretor> mapearDiretores(String linha) {
		return Arrays.stream(linha.split(","))
				.map(diretor -> new Diretor(diretor.trim()))
				.collect(Collectors.toList());
	}

	public static List<String> getAbreviacoes() {
		List<String> abreviacoes = new ArrayList<>();
		String destino = getDestino(TipoDeConsulta.ABREVIACOES);

		try {
			CsvReader csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
			csv.readHeaders();

			while (csv.readRecord()) {
				abreviacoes.add(csv.get("idioma") + "," + csv.get("abreviacao"));
			}
		} catch (IOException e) {
			logger.error("Arquivo não encontrado!");
		}

		return abreviacoes;
	}

	public static List<Diretor> getTodosOsDiretores() {
		List<Diretor> diretores = new ArrayList<>();
		String destino = getDestino(TipoDeConsulta.VISTOS);
		
		try {
			CsvReader csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
			csv.readHeaders();

			while (csv.readRecord()) {
				diretores.add(new Diretor(csv.get("diretor")));
			}
		} catch (IOException e) {
			logger.error("Arquivo não encontrado!");
		}
		
		return diretores;
	}
}
