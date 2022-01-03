package br.com.luis.apifilmes.models;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.luis.apifilmes.models.enums.Destino;

public class DestinoAtual {
	private static String pathAtual;
	
	public static String getPathAtual() {
		UriComponentsBuilder uri = ServletUriComponentsBuilder.fromCurrentRequest();
		pathAtual = uri.buildAndExpand().getPath();
		return pathAtual;
	}

	public static Destino getDestino() {
		getPathAtual();
		int ano = obterAnoDaRequisicao();
		
		if (ano == 2021) {
			return Destino.VISTOS_EM_2021;
		} else if (ano == 2022) {
			return Destino.VISTOS_EM_2022;
		}
		
		throw new IllegalArgumentException();
	}
	
	private static int obterAnoDaRequisicao() {
		return Integer.parseInt(pathAtual.split("/")[1]);
	}
}
