package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luis.apifilmes.models.utils.GeneroUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genero {
	String nome;
	
	@JsonIgnore
	public int getQuantidadeDeFilmes() {
		return GeneroUtils.getQuantidadeDeFilmes(this);
	}
}
