package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.interfaces.AcoesComFilmePendente;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.models.enums.Plataforma;

public class DefinidorDePlataforma implements AcoesComFilmePendente {
	private Destino destino;
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.PLATAFORMA;

	public DefinidorDePlataforma(Destino destino, CSVRecord record) {
		this.destino = destino;
		this.record = record;
	}
	
	public Plataforma definir() {
		if (ehFilmePendente()) {
			return null;
		}
		
		return Plataforma.valueOfPersonalizado(record.get(COLUNA.get()));
	}

	@Override
	public boolean ehFilmePendente() {
		return destino.equals(Destino.PENDENTES);
	}
}
