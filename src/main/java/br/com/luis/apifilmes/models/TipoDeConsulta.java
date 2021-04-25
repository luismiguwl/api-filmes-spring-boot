package br.com.luis.apifilmes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDeConsulta {
	VISTOS("filmes-2021\\Filmes assistidos em 2021.csv"), PENDENTES("filmes-2021\\Filmes pendentes para 2021.csv"),
	ABREVIACOES("filmes-2021\\Abreviacoes dos idiomas.csv");

	private String destino;
}
