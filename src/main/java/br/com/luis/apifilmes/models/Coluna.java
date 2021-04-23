package br.com.luis.apifilmes.models;

public enum Coluna {
	TITULO, DATA_ASSISTIDO, ANO_LANCAMENTO, IDIOMA, DIRETOR, GENERO, DURACAO;
	
	public static String getColuna(Coluna coluna) {
		
		// TODO: obter nome da coluna via Reflection
		switch (coluna) {
			case TITULO:
				return "titulo";
			case DATA_ASSISTIDO:
				return "dataAssistido";
			case ANO_LANCAMENTO:
				return "anoDeLancamento";
			case IDIOMA:
				return "idioma";
			case DIRETOR:
				return "diretor";
			case GENERO:
				return "genero";
			case DURACAO:
				return "duracao";
			default:
				return null;
		}
	}
}
