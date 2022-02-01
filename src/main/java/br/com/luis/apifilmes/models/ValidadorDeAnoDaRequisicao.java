package br.com.luis.apifilmes.models;

class ValidadorDeAnoDaRequisicao {
	private int ano;

	public ValidadorDeAnoDaRequisicao(int ano) {
		this.ano = ano;
	}

	public boolean validar() {
		final int ANO_MINIMO = 2021;
		int anoAtual = AnoAtual.get();

		return ano >= ANO_MINIMO && ano <= anoAtual;
	}
}