package br.com.luis.apifilmes.models.enums;

public enum Destino {
	VISTOS_EM_2021("dados/Filmes assistidos em 2021.csv"),
	VISTOS_EM_2022("dados/Filmes assistidos em 2022.csv"),
	PENDENTES("dados/Filmes pendentes para 2021.csv"),
	ABREVIACOES("dados/Abreviacoes dos idiomas.csv");

	private String destino;
	
	private Destino(String destino) {
		this.destino = destino;
	}
	
	public String get() {
		return destino;
	}
}
