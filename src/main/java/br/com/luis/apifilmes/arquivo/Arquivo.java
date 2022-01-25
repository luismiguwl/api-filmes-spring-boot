package br.com.luis.apifilmes.arquivo;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.enums.Destino.PENDENTES;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;
import br.com.luis.apifilmes.models.utils.MapeamentoUtils;

public class Arquivo {
	public static Iterable<CSVRecord> lerArquivoCsv(Destino destino) {
		try {
			Reader in = new FileReader(destino.get());
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

			return records;
		} catch (IOException e) {
			return null;
		}
	}

	public static void escreverFilmeNoArquivoCSV(FilmeVisto filme, Destino destino) {
		String[] headers = definirHeaders().split(",");
		List<Object> campos = new ArrayList<>();
		boolean ehFilmePendente = destino.equals(PENDENTES);
		
		campos.add(filme.getTitulo());
		
		if (!ehFilmePendente) {
			campos.add(filme.getDataEmQueFoiAssistido());
		}
		
		campos.add(Integer.toString(filme.getAnoDeLancamento()));
		campos.add(filme.getIdioma().getNome());
		
		String[] diretores = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, filme.getDiretores());
		campos.add(diretores.length > 0 ? String.join(", ", diretores) : diretores[0].trim());

		String[] generos = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Genero::getNome, filme.getGeneros());
		campos.add(generos.length > 0 ? String.join(", ", generos) : generos[0].trim());
	
		campos.add(Integer.toString(filme.getRuntime()));
		
		campos.add(filme.getPlataformaEmQueFoiAssistido().getPlataforma());
		
		campos.add(filme.getAssistidoLegendado());
		
		try {
			FileWriter out = new FileWriter(destino.get(), true);

			CSVPrinter csvPrinter = new CSVPrinter(out,
					CSVFormat.DEFAULT
					.withSkipHeaderRecord()
					.withHeader(headers));

			csvPrinter.printRecord(campos);

			csvPrinter.flush();
			csvPrinter.close();
			out.close();
		} catch (IOException e) {
			System.out.println("ERRO AO ESCREVER ARQUIVO!");
			e.printStackTrace();
		}
	}

	private static String definirHeaders() {
		String camposParaStringFormatada = definirCampoParaStringFormatada();
		String headers = String.format(
				camposParaStringFormatada, 
				TITULO.get(), 
				DATA_ASSISTIDO.get(), 
				ANO_LANCAMENTO.get(), 
				IDIOMA.get(), 
				DIRETOR.get(), 
				GENERO.get(), 
				DURACAO.get(),
				PLATAFORMA.get(),
				ASSISTIDO_LEGENDADO.get());
		
		return headers;
	}
	
	private static String definirCampoParaStringFormatada() {
		Coluna[] colunas = Coluna.values();
		String[] strings = new String[colunas.length];

		Arrays.fill(strings, "%s");
		
		return Arrays.stream(strings).collect(Collectors.joining(","));
	}
}
