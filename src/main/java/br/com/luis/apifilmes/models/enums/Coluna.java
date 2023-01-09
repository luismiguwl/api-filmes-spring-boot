package br.com.luis.apifilmes.models.enums;

public enum Coluna {
	TITULO("titulo"), DATA_ASSISTIDO("dataAssistido"), ANO_LANCAMENTO("anoDeLancamento"), IDIOMA("idioma"),
	DIRETOR("diretor"), GENERO("genero"), DURACAO("duracao"), PLATAFORMA("plataforma"),
	ASSISTIDO_LEGENDADO("assistidoLegendado"),	DATA_DE_ADICAO("dataEmQueFoiAdicionado"),
	LINK_IMDB("linkIMDB"), LINK_IMAGEM("linkImagem"), DESCRICAO("descricao"),
	ORCAMENTO("orcamento"), PRINCIPAIS_ATORES("principaisAtores"), CLASSIFICACAO("classificacao"),
	PAISES_DE_ORIGEM("paisDeOrigem");

	private String coluna;
	
	private Coluna(String coluna) {
		this.coluna = coluna;
	}
	
	public String get() {
		return coluna;
	}
}
