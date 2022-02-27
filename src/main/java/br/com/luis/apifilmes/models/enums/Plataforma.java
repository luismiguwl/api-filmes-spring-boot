package br.com.luis.apifilmes.models.enums;

import br.com.luis.apifilmes.utils.VerificadorDeString;

public enum Plataforma {
	NETFLIX("Netflix"), DISNEY_PLUS("Disney+", "Disney Plus"), HBO_MAX("HBO Max"), CINEMA("Cinema"), OUTROS("Outros"), INDEFINIDO("Indefinido");

	private String[] nomesPossiveis;
	
	private Plataforma(String... plataforma) {
		this.nomesPossiveis = plataforma;
	}
	
	public String[] getNomes() {
		return nomesPossiveis;
	}

	public static Plataforma valueOfPersonalizado(String texto) {
		if (texto.trim().isEmpty()) {
			return INDEFINIDO;
		}

		Plataforma[] plataformas = Plataforma.values();
		VerificadorDeString verificador = new VerificadorDeString();

		for (Plataforma plataforma : plataformas) {
			if (verificador.textoExisteNoArray(texto, plataforma.getNomes())) {
				return plataforma;
			}
		}
		
		throw new EnumConstantNotPresentException(Plataforma.class, texto);
	}
	
}