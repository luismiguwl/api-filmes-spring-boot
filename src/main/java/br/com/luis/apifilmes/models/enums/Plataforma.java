package br.com.luis.apifilmes.models.enums;

import java.util.Arrays;

import br.com.luis.apifilmes.utils.VerificadorDeString;

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
		VerificadorDeString verificador = new VerificadorDeString();

		for (Plataforma plataforma : plataformas) {
			if (verificador.textoExisteNoArray(texto, plataforma.getNomes())) {
				return plataforma;
			}
		}
		
		throw new IllegalArgumentException(String.format("%s não é um enum", texto));
	}
	
}