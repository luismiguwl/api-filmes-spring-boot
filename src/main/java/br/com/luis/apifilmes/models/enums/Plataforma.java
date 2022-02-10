package br.com.luis.apifilmes.models.enums;

import java.util.Arrays;

public enum Plataforma {
	NETFLIX("Netflix"), DISNEY_PLUS("Disney+", "Disney Plus"), HBO_MAX("HBO Max"), CINEMA("Cinema"), OUTROS("Outros");

	private String[] nomesPossiveis;
	
	private Plataforma(String... plataforma) {
		this.nomesPossiveis = plataforma;
	}
	
	public String getNome() {
		return nomesPossiveis[0];
	}
	
	public String[] getNomes() {
		return nomesPossiveis;
	}

	public static Plataforma valueOfPersonalizado(String texto) {
		if (texto.isEmpty() || texto.isBlank()) {
			return OUTROS;
		}

		Plataforma[] plataformas = Plataforma.values();

		for (Plataforma plataforma : plataformas) {
			if (textoPertenceAPlataforma(plataforma, texto)) {
				return plataforma;
			}
		}
		
		throw new IllegalArgumentException(String.format("%s não é um enum", texto));
	}
	
	private static boolean textoPertenceAPlataforma(Plataforma plataforma, String texto) {
		return Arrays.stream(plataforma.getNomes())
				.anyMatch(nome -> nome.equalsIgnoreCase(texto));
	}
}