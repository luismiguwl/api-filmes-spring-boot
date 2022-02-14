package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.interfaces.*;
import br.com.luis.apifilmes.models.enums.*;

public class DefinidorDeDataEmQueFoiAssistido implements AcoesComFilmePendente {
	private Destino destino;
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.DATA_ASSISTIDO;

	public DefinidorDeDataEmQueFoiAssistido(Destino destino, CSVRecord record) {
		this.destino = destino;
		this.record = record;
	}
	
	public String definir() {
		return ehFilmePendente() ? null : record.get(COLUNA.get()).trim();
	}
	
	@Override
	public boolean ehFilmePendente() {
		return destino.equals(Destino.PENDENTES);
	}
}
