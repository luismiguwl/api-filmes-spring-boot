package br.com.luis.apifilmes.arquivo;

import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Arquivo {
	public static Iterable<CSVRecord> lerArquivoCsv(String destino) {
		try {
			Reader in = new FileReader(destino);
			Iterable<CSVRecord> records = CSVFormat
					.DEFAULT
					.withFirstRecordAsHeader()
					.parse(in);
			
			return records;
		} catch (Exception e) {
			return null;
		}
	}
}
