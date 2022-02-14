package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.Duracao;
import br.com.luis.apifilmes.models.enums.Coluna;

public class DefinidorDeDuracao {
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.DURACAO;

	public DefinidorDeDuracao(CSVRecord record) {
		this.record = record;
	}
	
	public Duracao definir() {
		int duracao = Integer.parseInt(record.get(COLUNA.get()).trim());
		return duracao > 0 ? new Duracao(duracao) : null;
	}
}
