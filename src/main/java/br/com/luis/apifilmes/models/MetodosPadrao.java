package br.com.luis.apifilmes.models;

import java.util.List;

public interface MetodosPadrao {
	 Filme ultimo();
	 Filme random();
	 List<Filme> all();
	 List<Filme> filtrarPorIdioma(String idioma);
	 List<Filme> filtrarPorPalavraChave(String palavra);
	 List<Filme> buscarPorAnoDeLancamento(int ano);
	 List<Filme> buscarPorIntervaloDeAnos(int de, int ate);
	 void atualizarLista();
}
