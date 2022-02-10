package br.com.luis.apifilmes.models;

import java.util.List;

public interface Contador<T> {
	List<T> definirOcorrencias(List<T> lista, String... dados);
}
