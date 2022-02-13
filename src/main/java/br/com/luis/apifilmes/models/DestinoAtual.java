package br.com.luis.apifilmes.models;

import br.com.luis.apifilmes.interfaces.AcoesComFilmePendente;
import br.com.luis.apifilmes.models.enums.Destino;

public class DestinoAtual implements AcoesComFilmePendente {
	private PathAtual pathAtual;

	public DestinoAtual(PathAtual pathAtual) {
		this.pathAtual = pathAtual;
	}

	public Destino getDestino() {
		if (ehFilmePendente()) {
			return Destino.PENDENTES;
		} else if (validarAnoDaRequisicaoAtual()) {
			int ano = obterAnoDaRequisicao();
			return Destino.obterDestinoBaseadoNoAno(ano);
		}

		throw new IllegalArgumentException("Path inválido");		
	}

	@Override
	public boolean ehFilmePendente() {
		return pathAtual.get().split("/")[1].equals("pendentes");
	}
	
	public int obterAnoDaRequisicao() {
		try {
			return Integer.parseInt(pathAtual.get().split("/")[1]);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Informe um valor inteiro!");
		}
	}

	public boolean validarAnoDaRequisicaoAtual() {
		int ano = obterAnoDaRequisicao();
		return new ValidadorDeAnoDaRequisicao(ano).validar();
	}
	
}
