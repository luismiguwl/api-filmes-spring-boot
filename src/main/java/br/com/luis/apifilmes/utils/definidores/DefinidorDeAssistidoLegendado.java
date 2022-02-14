package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.Coluna;

public class DefinidorDeAssistidoLegendado {
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.ASSISTIDO_LEGENDADO;

	public DefinidorDeAssistidoLegendado(CSVRecord record) {
		this.record = record;
	}
	
	public Boolean definir() {
		boolean encontrouColuna = record.isMapped("assistidoLegendado");
		return encontrouColuna ? record.get(COLUNA.get()).equals("true") : null;
	}
}
