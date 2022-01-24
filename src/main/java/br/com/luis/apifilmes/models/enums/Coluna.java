package br.com.luis.apifilmes.models.enums;

public enum Coluna {
	TITULO("titulo"), DATA_ASSISTIDO("dataAssistido"), ANO_LANCAMENTO("anoDeLancamento"), IDIOMA("idioma"),
	DIRETOR("diretor"), GENERO("genero"), DURACAO("duracao"), ABREVIACAO("abreviacao"), PLATAFORMA("plataforma"), ASSISTIDO_LEGENDADO("assistidoLegendado");

	private String coluna;
	
	private Coluna(String coluna) {
		this.coluna = coluna;
	}
	
	public String get() {
		return coluna;
	}
}
