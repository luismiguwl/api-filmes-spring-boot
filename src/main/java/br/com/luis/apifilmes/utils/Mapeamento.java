package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.*;

public class Mapeamento {
	public static List<Filme> getFilmes(TipoDeConsulta tipo) {
		List<Filme> filmes = new ArrayList<>();
		String destino = tipo.getDestino();

		Iterable<CSVRecord> records = lerArquivoCsv(destino);

		for (CSVRecord record : records) {
			String data = null;

			List<Diretor> diretores = new ArrayList<>();
			Diretor diretor = null;

			List<Genero> generos = new ArrayList<>();
			Genero genero = null;

			String titulo = record.get(Coluna.TITULO.getColuna());

			if (tipo.equals(TipoDeConsulta.VISTOS)) {
				data = record.get(Coluna.DATA_ASSISTIDO.getColuna());
			}

			int ano = Integer.parseInt(record.get(Coluna.ANO_LANCAMENTO.getColuna()));
			Idioma idioma = new Idioma(record.get(Coluna.IDIOMA.getColuna()));

			if (record.get(Coluna.DIRETOR.getColuna()).contains(",")) {
				diretores = mapearDiretores(record.get(Coluna.DIRETOR.getColuna()));
			} else {
				diretor = new Diretor(record.get(Coluna.DIRETOR.getColuna()));
			}

			int runtime = Integer.parseInt(record.get(Coluna.DURACAO.getColuna()).trim());

			if (record.get(Coluna.GENERO.getColuna()).contains(",")) {
				generos = mapearGeneros(record.get(Coluna.GENERO.getColuna()));
			} else {
				genero = new Genero(record.get(Coluna.GENERO.getColuna()));
			}

			Filme filme = new Filme(titulo, ano, data, idioma, diretor, diretores, genero, generos, runtime);
			filmes.add(filme);
		}

		return filmes;
	}

	public static List<String> getAbreviacoes() {
		List<String> abreviacoes = new ArrayList<>();
		String destino = TipoDeConsulta.ABREVIACOES.getDestino();

		Iterable<CSVRecord> records = lerArquivoCsv(destino);

		for (CSVRecord record : records) {
			abreviacoes.add(record.get("idioma") + "," + record.get("abreviacao"));
		}

		return abreviacoes;
	}

	public static List<String> getDadosDaColuna(Coluna coluna) {
		List<String> dadosDaColuna = new ArrayList<>();
		String destino = TipoDeConsulta.VISTOS.getDestino();
		String nomeDaColuna = coluna.getColuna();

		Iterable<CSVRecord> records = lerArquivoCsv(destino);

		for (CSVRecord record : records) {
			dadosDaColuna.add(record.get(nomeDaColuna));
		}

		return dadosDaColuna;
	}

	private static Iterable<CSVRecord> lerArquivoCsv(String destino) {
		try {
			Reader in = new FileReader(destino);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
			return records;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
