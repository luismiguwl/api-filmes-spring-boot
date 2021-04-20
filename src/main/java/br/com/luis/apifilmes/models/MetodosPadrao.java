package br.com.luis.apifilmes.models;

import java.util.List;

public abstract class MetodosPadrao {
	public abstract Filme ultimo();
	public abstract Filme random();
	public abstract List<Filme> all();
	public abstract List<Filme> filtrarPorIdioma(String idioma);
	public abstract List<Filme> filtrarPorPalavraChave(String palavra);
	public abstract List<Filme> buscarPorAnoDeLancamento(int ano);
	public abstract List<Filme> buscarPorIntervaloDeAnos(int de, int ate);
}
