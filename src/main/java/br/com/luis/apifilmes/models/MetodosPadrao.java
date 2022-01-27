package br.com.luis.apifilmes.models;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MetodosPadrao<T> {
	 ResponseEntity<T> obterUltimoFilmeVisto();
	 ResponseEntity<T> obterFilmeAleatorio();
	 ResponseEntity<List<T>> obterTodosOsFilmes();
	 ResponseEntity<List<T>> filtrarFilmePorPalavraChave(String palavra);
	 ResponseEntity<List<T>> buscarFilmePorAnoDeLancamento(int ano);
	 ResponseEntity<List<T>> buscarFilmePorIntervaloDeAnos(int de, int ate);
	 void converterFilmesGenericosParaFilmeEspecifico(List<Filme> filmesGenericos);
}
