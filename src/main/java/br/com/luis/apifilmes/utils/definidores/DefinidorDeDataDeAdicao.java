package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.*;

public class DefinidorDeDataDeAdicao {
	private Destino destino;
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.DATA_DE_ADICAO;

	public DefinidorDeDataDeAdicao(Destino destino, CSVRecord record) {
		this.destino = destino;
		this.record = record;
	}

	public String definir() {
		if (destino.ehFilmePendente()) {
			String valor = record.get(COLUNA.get());
			return valor.isBlank() ? null : valor;
		}

		return null;
	}

}
