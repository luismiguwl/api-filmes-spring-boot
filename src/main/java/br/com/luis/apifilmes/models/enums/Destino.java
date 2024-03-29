package br.com.luis.apifilmes.models.enums;

import br.com.luis.apifilmes.interfaces.AcoesComFilmePendente;
import br.com.luis.apifilmes.models.AnoAtual;
import br.com.luis.apifilmes.utils.VerificadorDeString;

public enum Destino implements AcoesComFilmePendente {
	VISTOS_EM_2021("dados/filmes-assistidos-em-2021.csv"),
	VISTOS_EM_2022("dados/filmes-assistidos-em-2022.csv"),
	VISTOS_EM_2023("dados/filmes-assistidos-em-2023.csv"),
	PENDENTES("dados/filmes-pendentes.csv"),
	ABREVIACOES("dados/abreviacoes-dos-idiomas.csv");

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

		throw new EnumConstantNotPresentException(Destino.class, nome);
	}

	public static boolean enumExiste(String nome) {
		return new VerificadorDeString().textoExisteNoArray(nome, getNomes());
	}
	
	private static String[] getNomes() {
		Destino[] destinos = Destino.values();
		String[] nomes = new String[destinos.length];
		
		for (int i = 0; i < nomes.length; i++) {
			nomes[i] = destinos[i].name();
		}
		
		return nomes;
	}

	public static Destino obterDestinoBaseadoNoAnoAtual() {
		int anoAtual = new AnoAtual().get();
		String nome = obterModeloDeNomeParaFilmeVistoBaseadoNoAno(anoAtual);
		return Destino.valueOf(nome);
	}

	private static String obterModeloDeNomeParaFilmeVistoBaseadoNoAno(int ano) {
		return String.format("%s%d", MODELO_PADRAO_DESTINO_PARA_FILME_VISTOS, ano);
	}
	
	@Override
	public boolean ehFilmePendente() {
		return this.equals(PENDENTES);
	}
}
