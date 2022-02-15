package br.com.luis.apifilmes.arquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.com.luis.apifilmes.models.Filme;
import br.com.luis.apifilmes.models.enums.Destino;

public class EscritorDeCSV {
	private Destino destino;
	
	public EscritorDeCSV(Destino destino) {
		this.destino = destino;
	}
	
	public void escreverFilmeNoArquivoCSV(Filme filme) {
		GeradorDeCabecalho gerador = new GeradorDeCabecalho(filme);
		String[] headers = gerador.gerar();
		
		PreenchedorDeCampos preenchedor = new PreenchedorDeCampos(filme);
		List<Object> campos = preenchedor.preencher();
		
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
}
