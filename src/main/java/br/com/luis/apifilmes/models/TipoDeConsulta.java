package br.com.luis.apifilmes.models;

public enum TipoDeConsulta {
	VISTOS, PENDENTES, ABREVIACOES;

	public static String getDestino(TipoDeConsulta tipo) {
		switch (tipo) {
			case VISTOS:
				return "filmes-2021\\Filmes assistidos em 2021.csv";
			case PENDENTES:
				return "filmes-2021\\Filmes pendentes para 2021.csv";
			case ABREVIACOES:
				return "filmes-2021\\Abreviacoes dos idiomas.csv";
			default:
				return null;
			}
	}
}
