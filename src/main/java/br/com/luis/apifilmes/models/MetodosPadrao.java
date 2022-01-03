package br.com.luis.apifilmes.models;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MetodosPadrao {
	 ResponseEntity<Filme> obterUltimoFilmeVisto();
	 ResponseEntity<Filme> obterFilmeAleatorio();
	 ResponseEntity<List<Filme>> obterTodosOsFilmes();
	 ResponseEntity<List<Filme>> filtrarFilmePorPalavraChave(String palavra);
	 ResponseEntity<List<Filme>> buscarFilmePorAnoDeLancamento(int ano);
	 ResponseEntity<List<Filme>> buscarFilmePorIntervaloDeAnos(int de, int ate);
}
