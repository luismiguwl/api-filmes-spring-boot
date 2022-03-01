package br.com.luis.apifilmes.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.luis.apifilmes.models.Filme;

public interface ControllerDeFilme<T> {
	 ResponseEntity<T> obterUltimoFilmeVisto();
	 ResponseEntity<T> obterFilmeAleatorio();
	 ResponseEntity<List<T>> obterTodosOsFilmes();
	 ResponseEntity<List<T>> filtrarFilmePorPalavraChave(String palavra);
	 ResponseEntity<List<T>> buscarFilmePorAnoDeLancamento(int ano);
	 ResponseEntity<List<T>> buscarFilmePorAnoDeLancamento(int de, int ate);
	 void converterFilmesGenericosParaFilmesEspecificos(List<Filme> filmesGenericos);
}
