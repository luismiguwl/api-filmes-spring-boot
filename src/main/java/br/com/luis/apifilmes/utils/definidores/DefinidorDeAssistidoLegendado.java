package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.Coluna;

public class DefinidorDeAssistidoLegendado {
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.ASSISTIDO_LEGENDADO;

	public DefinidorDeAssistidoLegendado(CSVRecord record) {
		this.record = record;
	}
	
	public Integer definir() {
		boolean encontrouColuna = record.isMapped(COLUNA.get());

		if (!encontrouColuna) {
			return null;
		}

		return record.get(COLUNA.get()).isEmpty() ? null : Integer.parseInt(record.get(COLUNA.get()));
	}
}
