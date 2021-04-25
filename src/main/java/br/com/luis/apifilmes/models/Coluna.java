package br.com.luis.apifilmes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Coluna {
	TITULO("titulo"), DATA_ASSISTIDO("dataAssistido"), ANO_LANCAMENTO("anoDeLancamento"), IDIOMA("idioma"),
	DIRETOR("diretor"), GENERO("genero"), DURACAO("duracao");

	private String coluna;
}
