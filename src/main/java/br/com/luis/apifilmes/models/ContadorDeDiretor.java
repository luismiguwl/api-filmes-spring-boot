package br.com.luis.apifilmes.models;

import java.util.Arrays;
import java.util.List;

public class ContadorDeDiretor implements Contador<Diretor> {
	@Override
	public List<Diretor> definirOcorrencias(List<Diretor> diretores, String... dados) {
		for (Diretor diretor : diretores) {
			int quantidade = (int) Arrays.stream(dados)
					.filter(nome -> nome.equals(diretor.getNome()))
					.count();
			
			if (quantidade > 0) {
				diretor.setQuantidadeDeFilmesVistos(quantidade);
			}
		}
		
		return diretores;
	}

	
}
