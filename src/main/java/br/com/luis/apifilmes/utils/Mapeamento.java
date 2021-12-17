package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.enums.Destino.*;
import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.utils.MapeamentoUtils;
import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.arquivo.Arquivo;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.models.enums.Destino;

public class Mapeamento {
	public static List<Filme> getFilmes(Destino destino) {
		List<Filme> filmes = new ArrayList<>();
		Iterable<CSVRecord> records = Arquivo.lerArquivoCsv(destino);

		for (CSVRecord record : records) {
			String titulo = record.get(TITULO.get().trim());

			String data = null;
			if (destino.equals(VISTOS)) {
				data = record.get(DATA_ASSISTIDO.get().trim());
			}

			int ano = Integer.parseInt(record.get(ANO_LANCAMENTO.get()).trim());
			int runtime = Integer.parseInt(record.get(DURACAO.get()).trim());
			
			Idioma idioma = new Idioma(record.get(IDIOMA.get()));

			List<Diretor> diretores = obterListaDeObjetosBaseadoNaString(Diretor::new, record.get(DIRETOR.get()));
			List<Genero> generos = obterListaDeObjetosBaseadoNaString(Genero::new, record.get(GENERO.get()));

			Filme filme = new Filme(titulo, ano, data, idioma, diretores, generos, runtime);
			filmes.add(filme);
		}

		return filmes;
	}

	public static String[] getDadosDaColuna(Coluna... colunas) {
		Destino destino = obterDestinoBaseadoNasColunas(colunas);
		Iterable<CSVRecord> records = Arquivo.lerArquivoCsv(destino);

		List<String> dadosDaColuna = new ArrayList<>();

		for (CSVRecord record : records) {
			String dado;
			
			if (colunas.length > 1) {
				dado = obterLinhaContendoDadosDasColunasInserindoVirgulaEntreElesSeForMaisDeUmaColuna(record, colunas);
			} else {
				String coluna = colunas[0].get();
				dado = record.get(coluna);
			}
			
			dadosDaColuna.add(dado);
		}

		String[] dados = MapeamentoUtils.converterListaDeStringParaArray(dadosDaColuna);
		return dados;
	}
	
	private static Destino obterDestinoBaseadoNasColunas(Coluna[] colunas) {
		boolean colunaDeAbreviacaoEncontrada = 
				Arrays.asList(colunas).stream()
				.anyMatch(coluna -> coluna.equals(ABREVIACAO));
		
		return colunaDeAbreviacaoEncontrada ? ABREVIACOES : VISTOS;
	}
	
	private static String obterLinhaContendoDadosDasColunasInserindoVirgulaEntreElesSeForMaisDeUmaColuna(CSVRecord record, Coluna[] colunas) {
		String[] dados = new String[colunas.length];
		
		for (int i = 0; i < colunas.length; i++) {
			String coluna = colunas[i].get();
			dados[i] = record.get(coluna);
		}

		return aplicarVirgulaSeNecessario(dados);
	}
	
	private static String aplicarVirgulaSeNecessario(String[] dados) {
		if (dados.length > 1) {
			return Arrays.asList(dados).stream()
					.collect(Collectors.joining(","));
		}
		
		return dados[0];
	}
}
