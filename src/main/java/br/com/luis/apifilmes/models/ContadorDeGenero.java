package br.com.luis.apifilmes.models;

import java.util.Arrays;
import java.util.List;

import br.com.luis.apifilmes.interfaces.Contador;

public class ContadorDeGenero implements Contador<Genero> {
	@Override
	public List<Genero> definirOcorrencias(List<Genero> generos, String... dados) {
		for (Genero genero : generos) {
			int quantidade = (int) Arrays.stream(dados)
					.filter(nome -> nome.equals(genero.getNome()))
					.count();
			
			if (quantidade > 0) {
				genero.setQuantidadeDeFilmes(quantidade);
			}
		}
		
		return generos;
	}
}
