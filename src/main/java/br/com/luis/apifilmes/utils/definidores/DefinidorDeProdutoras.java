package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.Produtora;
import br.com.luis.apifilmes.models.enums.Coluna;
import java.util.*;

public class DefinidorDeProdutoras {
	private CSVRecord record;
	private final Coluna COLUNA = Coluna.PRODUTORA;

	public DefinidorDeProdutoras(CSVRecord record) {
		this.record = record;
	}
	
	public List<Produtora> definir() {
		final String valorDaColuna = record.get(COLUNA.get());

		if (valorDaColuna.isEmpty() || valorDaColuna.isBlank()) {
			return null;
		}

		List<Produtora> produtoras = new ArrayList<>();
		for (String produtora : valorDaColuna.split(",")) {
			produtoras.add(new Produtora(produtora.trim()));
		}

		return produtoras;
	}
}
