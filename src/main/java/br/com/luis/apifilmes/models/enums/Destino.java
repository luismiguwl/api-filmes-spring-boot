package br.com.luis.apifilmes.models.enums;

public enum Destino {
	VISTOS("/home/luis/ondrive/Documentos do Eclipse/api-filmes-spring-boot/filmes-2021/Filmes assistidos em 2021.csv"), PENDENTES("/home/luis/ondrive/Documentos do Eclipse/api-filmes-spring-boot/filmes-2021/Filmes pendentes para 2021.csv"),
	ABREVIACOES("/home/luis/ondrive/Documentos do Eclipse/api-filmes-spring-boot/filmes-2021/Abreviacoes dos idiomas.csv");

	private String destino;
	
	private Destino(String destino) {
		this.destino = destino;
	}
	
	public String get() {
		return destino;
	}
}
