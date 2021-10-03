package br.com.luis.apifilmes.arquivo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.Destino;

public class Arquivo {
	public static Iterable<CSVRecord> lerArquivoCsv(Destino destino) {
		try {
			Reader in = new FileReader(destino.get());
			Iterable<CSVRecord> records = CSVFormat
					.DEFAULT
					.withFirstRecordAsHeader()
					.parse(in);
			
			return records;
		} catch (IOException e) {
			return null;
		}
	}
}