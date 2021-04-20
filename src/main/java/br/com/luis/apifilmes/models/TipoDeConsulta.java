package br.com.luis.apifilmes.models;

public enum TipoDeConsulta {
	VISTOS, PENDENTES, ABREVIACOES;

	private String destino;

	public String getDestino(TipoDeConsulta tipo) {
		switch (tipo) {
			case VISTOS:
				destino = "filmes-2021\\Filmes assistidos em 2021.csv";
				break;
			case PENDENTES:
				destino = "filmes-2021\\Filmes pendentes para 2021.csv";
				break;
			case ABREVIACOES:
				destino = "filmes-2021\\Abreviacoes dos idiomas.csv";
				break;
		}

		return destino;
	}

}
