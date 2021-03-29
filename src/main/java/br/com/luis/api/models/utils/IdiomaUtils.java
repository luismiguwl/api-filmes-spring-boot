package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.api.arquivo.Arquivo;

public class IdiomaUtils {
	public static String getAbreviacao(String idioma) {
		String destino = "C:\\Users\\Luis Miguel\\OneDrive\\Documentos do Eclipse\\api-filmes-spring-boot\\filmes-2021\\Abreviacoes dos idiomas.csv";
		List<String> listaDeAbreviacoes = Arquivo.lerArquivo(destino);
		
		return listaDeAbreviacoes.stream()
		    .filter(abreviacao -> abreviacao.split(",")[0].equals(idioma))
		    .map(abreviacao -> abreviacao.split(",")[1])
		    .collect(Collectors.toList())
		    .get(0);
	}
}