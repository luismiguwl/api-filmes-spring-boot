package br.com.luis.api.models;

import java.util.List;

public abstract class MetodosPadrao {
	public abstract List<Filme> all();
	public abstract Filme random();
	public abstract List<Filme> filtrarPorIdioma(String idioma);
	public abstract List<Filme> filtrarPorMes(int mes);
	public abstract Filme ultimo();
	public abstract List<Filme> filtrarPorPalavraChave(String palavra);
}
