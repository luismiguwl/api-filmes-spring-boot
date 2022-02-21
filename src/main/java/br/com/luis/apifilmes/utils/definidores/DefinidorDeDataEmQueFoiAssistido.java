package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.*;

public class DefinidorDeDataEmQueFoiAssistido {
	private Destino destino;
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.DATA_ASSISTIDO;

	public DefinidorDeDataEmQueFoiAssistido(Destino destino, CSVRecord record) {
		this.destino = destino;
		this.record = record;
	}
	
	public String definir() {
		return destino.ehFilmePendente() ? null : record.get(COLUNA.get()).trim();
	}
	
}
