package br.com.luis.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.api.models.utils.MesUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_EMPTY)
@Data
public class Mes {
	private String nome;

	@JsonIgnore
	private int numeroDoMes;

	public Mes(String nome, int numeroDoMes) {
		super();
		this.nome = nome;
		this.numeroDoMes = numeroDoMes;
	}

}
