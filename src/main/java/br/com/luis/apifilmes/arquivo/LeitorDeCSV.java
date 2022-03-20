package br.com.luis.apifilmes.arquivo;

import java.io.*;
import org.apache.commons.csv.*;
import br.com.luis.apifilmes.models.enums.*;

public class LeitorDeCSV {
	private Destino destino;
	
	public LeitorDeCSV(Destino destino) {
		this.destino = destino;
	}
	
	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Iterable<CSVRecord> ler() {
		try {
			Reader in = new FileReader(destino.get());
			Iterable<CSVRecord> records = CSVFormat
					.DEFAULT
					.withFirstRecordAsHeader()
					.parse(in);

			return records;
		} catch (IOException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
}
