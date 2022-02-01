package br.com.luis.apifilmes.models;

import br.com.luis.apifilmes.models.enums.Destino;
import br.com.luis.apifilmes.models.*;

public class DestinoAtual {
	private String pathAtual;

	public DestinoAtual() {
		this.pathAtual = PathAtual.get();
	}

	public Destino getDestino() {
		if (destinoAtualEhPraFilmePendente()) {
			return Destino.PENDENTES;
		} else if (validarAnoDaRequisicaoAtual()) {
			int ano = obterAnoDaRequisicao();
			return Destino.obterDestinoBaseadoNoAno(ano);
		}

		throw new IllegalArgumentException("Path inválido");		
	}

	private boolean destinoAtualEhPraFilmePendente() {
		return pathAtual.contains("/pendentes");
	}
	
	public int obterAnoDaRequisicao() {
		try {
			return Integer.parseInt(pathAtual.split("/")[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Informe um valor inteiro!");
		}
	}

	public boolean validarAnoDaRequisicaoAtual() {
		int ano = obterAnoDaRequisicao();
		return new ValidadorDeAnoDaRequisicao(ano).validar();
	}
}
