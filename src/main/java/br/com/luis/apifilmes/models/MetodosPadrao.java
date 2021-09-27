package br.com.luis.apifilmes.models;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MetodosPadrao {
	 ResponseEntity<Filme> ultimo();
	 ResponseEntity<Filme> random();
	 ResponseEntity<List<Filme>> all();
	 ResponseEntity<List<Filme>> filtrarPorIdioma(String idioma);
	 ResponseEntity<List<Filme>> filtrarPorPalavraChave(String palavra);
	 ResponseEntity<List<Filme>> buscarPorAnoDeLancamento(int ano);
	 ResponseEntity<List<Filme>> buscarPorIntervaloDeAnos(int de, int ate);
}
