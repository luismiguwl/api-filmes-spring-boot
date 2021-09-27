package br.com.luis.apifilmes.models;

public enum Coluna {
	TITULO("titulo"), DATA_ASSISTIDO("dataAssistido"), ANO_LANCAMENTO("anoDeLancamento"), IDIOMA("idioma"),
	DIRETOR("diretor"), GENERO("genero"), DURACAO("duracao"), ABREVIACAO("abreviacao");

	private String coluna;
	
	private Coluna(String coluna) {
		this.coluna = coluna;
	}
	
	public String getColuna() {
		return coluna;
	}
}
