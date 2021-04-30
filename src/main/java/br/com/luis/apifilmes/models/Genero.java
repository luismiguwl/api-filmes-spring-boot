package br.com.luis.apifilmes.models;

import br.com.luis.apifilmes.models.utils.GeneroUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genero {
	String nome;
	
	public int getQuantidadeDeFilmes() {
		return GeneroUtils.getQuantidadeDeFilmes(this);
	}
}
