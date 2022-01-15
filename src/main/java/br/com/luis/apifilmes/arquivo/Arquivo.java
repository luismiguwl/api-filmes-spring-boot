package br.com.luis.apifilmes.arquivo;

import static br.com.luis.apifilmes.models.enums.Coluna.ANO_LANCAMENTO;
import static br.com.luis.apifilmes.models.enums.Coluna.DATA_ASSISTIDO;
import static br.com.luis.apifilmes.models.enums.Coluna.DIRETOR;
import static br.com.luis.apifilmes.models.enums.Coluna.DURACAO;
import static br.com.luis.apifilmes.models.enums.Coluna.GENERO;
import static br.com.luis.apifilmes.models.enums.Coluna.IDIOMA;
import static br.com.luis.apifilmes.models.enums.Coluna.PLATAFORMA;
import static br.com.luis.apifilmes.models.enums.Coluna.TITULO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.Genero;
import br.com.luis.apifilmes.models.enums.Destino;
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

	public static void escreverFilmeNoArquivoCSV(Filme filme, Destino destino) {
		String[] headers = definirHeadersBaseadoNoObjeto(filme).split(",");
		List<String> campos = new ArrayList<>();
		
		campos.add(filme.getTitulo());
		
		if (!destino.equals(Destino.PENDENTES)) {
			campos.add(filme.getDataEmQueFoiAssistido());
		}
		
		campos.add(Integer.toString(filme.getAnoDeLancamento()));
		campos.add(filme.getIdioma().getNome());
		
		String[] diretores = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, filme.getDiretores());
		campos.add(diretores.length > 0 ? String.join(", ", diretores) : diretores[0].trim());

		String[] generos = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Genero::getNome, filme.getGeneros());
		campos.add(generos.length > 0 ? String.join(", ", generos) : generos[0].trim());
	
		campos.add(Integer.toString(filme.getRuntime()));
		
		if (filme.getPlataformaEmQueFoiAssistido() != null) {
			campos.add(filme.getPlataformaEmQueFoiAssistido());
		}
		
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

	private static String definirHeadersBaseadoNoObjeto(Filme filme) {
		String headers = String.format(
				"%s,%s,%s,%s,%s,%s,%s,%s", 
				TITULO.get(), 
				DATA_ASSISTIDO.get(), 
				ANO_LANCAMENTO.get(), 
				IDIOMA.get(), 
				DIRETOR.get(), 
				GENERO.get(), 
				DURACAO.get(),
				PLATAFORMA.get());
		
		if (filme.getPlataformaEmQueFoiAssistido() == null) {
			headers = headers.replace(PLATAFORMA.get() + ",", "");
		}
		
		if (filme.getDataEmQueFoiAssistido() == null) {
			headers = headers.replace(DATA_ASSISTIDO.get() + ",", "");
		}
		
		return headers;
	}
}
