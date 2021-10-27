package br.com.luis.apifilmes.models.enums;

public enum Destino {
	VISTOS("filmes-2021/Filmes assistidos em 2021.csv"), PENDENTES("filmes-2021/Filmes pendentes para 2021.csv"),
	ABREVIACOES("filmes-2021/Abreviacoes dos idiomas.csv");

	private String destino;
	
	private Destino(String destino) {
		this.destino = destino;
	}
	
	public String get() {
		return destino;
	}
}
