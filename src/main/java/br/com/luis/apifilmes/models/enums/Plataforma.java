package br.com.luis.apifilmes.models.enums;

public enum Plataforma {
	NETFLIX("Netflix"), DISNEY_PLUS("Disney+"), HBO_MAX("HBO Max"), CINEMA("Cinema"), OUTROS("Outros");

	private String plataforma;

	private Plataforma(String plataforma) {
		this.plataforma = plataforma;
	}	

	public String getPlataforma() {
		return plataforma;
	}
}