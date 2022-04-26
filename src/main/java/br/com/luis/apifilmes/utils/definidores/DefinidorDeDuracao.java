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
		String supostaDuracao = record.get(COLUNA.get()).trim();
		return (supostaDuracao.isEmpty() || Integer.parseInt(supostaDuracao) == 0) ? null : new Duracao(Integer.parseInt(supostaDuracao));
	}
}
