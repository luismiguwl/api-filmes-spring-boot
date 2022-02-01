package br.com.luis.apifilmes.models.enums;

import java.util.Arrays;
import br.com.luis.apifilmes.models.AnoAtual;

public enum Destino {
	VISTOS_EM_2021("dados/Filmes assistidos em 2021.csv"),
	VISTOS_EM_2022("dados/Filmes assistidos em 2022.csv"),
	PENDENTES("dados/Filmes pendentes.csv"),
	ABREVIACOES("dados/Abreviacoes dos idiomas.csv");

	private static final String MODELO_PADRAO_DESTINO_PARA_FILME_VISTOS = "VISTOS_EM_";
	private String destino;
	
	private Destino(String destino) {
		this.destino = destino;
	}
	
	public String get() {
		return destino;
	}

	public static Destino obterDestinoBaseadoNoAno(int ano) {
		String nome = obterModeloDeNomeParaFilmeVistoBaseadoNoAno(ano);

		if (enumExiste(nome)) {
			return Destino.valueOf(nome);
		}

		throw new IllegalArgumentException(String.format("O enum %s não existe!",nome));
	}

	private static boolean enumExiste(String nome) {
		return Arrays.stream(Destino.values())
				.anyMatch(destino -> destino.name().equals(nome));
	}

	public static Destino obterDestinoBaseadoNoAnoAtual() {
		int anoAtual = AnoAtual.get();
		String nome = obterModeloDeNomeParaFilmeVistoBaseadoNoAno(anoAtual);
		return Destino.valueOf(nome);
	}

	private static String obterModeloDeNomeParaFilmeVistoBaseadoNoAno(int ano) {
		return String.format("%s%d", MODELO_PADRAO_DESTINO_PARA_FILME_VISTOS, ano);
	}
}
