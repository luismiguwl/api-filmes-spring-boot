package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.arquivo.Arquivo;
import br.com.luis.apifilmes.models.*;
import static br.com.luis.apifilmes.models.Coluna.*;

public class Mapeamento {
	public static List<Filme> getFilmes(TipoDeConsulta tipo) {
		List<Filme> filmes = new ArrayList<>();
		String destino = tipo.getDestino();

		Iterable<CSVRecord> records = Arquivo.lerArquivoCsv(destino);

		for (CSVRecord record : records) {
			String data = null;

			List<Diretor> diretores = new ArrayList<>();
			Diretor diretor = null;

			List<Genero> generos = new ArrayList<>();
			Genero genero = null;

			String titulo = record.get(TITULO.getColuna());

			if (tipo.equals(TipoDeConsulta.VISTOS)) {
				data = record.get(DATA_ASSISTIDO.getColuna());
			}

			int ano = Integer.parseInt(record.get(ANO_LANCAMENTO.getColuna()));
			Idioma idioma = new Idioma(record.get(IDIOMA.getColuna()));

			if (record.get(DIRETOR.getColuna()).contains(",")) {
				diretores = obterListaContendoNomeDeCadaDiretorBaseadoNumaString(record.get(DIRETOR.getColuna()));
			} else {
				diretor = new Diretor(record.get(DIRETOR.getColuna()));
			}

			int runtime = Integer.parseInt(record.get(DURACAO.getColuna()).trim());

			if (record.get(GENERO.getColuna()).contains(",")) {
				generos = obterListaContendoCadaGeneroBaseadoNumaString(record.get(GENERO.getColuna()));
			} else {
				genero = new Genero(record.get(GENERO.getColuna()));
			}

			Filme filme = new Filme(titulo, ano, data, idioma, diretor, diretores, genero, generos, runtime);
			filmes.add(filme);
		}

		return filmes;
	}

	public static List<String> getDadosDaColuna(Coluna... colunas) {
		List<String> dadosDaColuna = new ArrayList<>();
		String destino = obterDestinoBaseadoNasColunas(colunas);

		Iterable<CSVRecord> records = Arquivo.lerArquivoCsv(destino);

		for (CSVRecord record : records) {
			String dado;
			
			if (colunas.length > 1) {
				dado = obterLinhaContendoDadosDasColunasInserindoVirgulaEntreElesSeForMaisDeUmaColuna(record, colunas);
			} else {
				String coluna = colunas[0].getColuna();
				dado = record.get(coluna);
			}
			
			dadosDaColuna.add(dado);
		}

		return dadosDaColuna;
	}
	
	private static String obterDestinoBaseadoNasColunas(Coluna[] colunas) {
		boolean colunaDeAbreviacaoEncontrada = 
				Arrays.asList(colunas).stream()
				.anyMatch(coluna -> coluna.equals(ABREVIACAO));
		
		if (colunaDeAbreviacaoEncontrada) {
			return TipoDeConsulta.ABREVIACOES.getDestino();
		} else {
			return TipoDeConsulta.VISTOS.getDestino();
		}
	}
	
	private static String obterLinhaContendoDadosDasColunasInserindoVirgulaEntreElesSeForMaisDeUmaColuna(CSVRecord record, Coluna[] colunas) {
		String[] dados = new String[colunas.length];
		
		for (int i = 0; i < colunas.length; i++) {
			String coluna = colunas[i].getColuna();
			dados[i] = record.get(coluna);
		}
		
		String linhaContendoDados = aplicarVirgulaSeNecessario(dados);
		return linhaContendoDados;
	}
	
	private static String aplicarVirgulaSeNecessario(String[] dados) {
		if (dados.length > 1) {
			String linha = dados[0];
			
			for (int i = 1; i < dados.length; i++) {
				linha += "," + dados[i];
			}
			
			return linha;
		}
		
		return dados[0];
	}
}
