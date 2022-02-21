package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.*;

public class DefinidorDePlataforma {
	private Destino destino;
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.PLATAFORMA;

	public DefinidorDePlataforma(Destino destino, CSVRecord record) {
		this.destino = destino;
		this.record = record;
	}
	
	public Plataforma definir() {
		if (destino.ehFilmePendente()) {
			return null;
		}
		
		return Plataforma.valueOfPersonalizado(record.get(COLUNA.get()));
	}
}
