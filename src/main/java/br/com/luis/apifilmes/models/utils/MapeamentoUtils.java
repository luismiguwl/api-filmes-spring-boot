package br.com.luis.apifilmes.models.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.models.Genero;

public class MapeamentoUtils {
	public static List<Diretor> obterListaContendoNomeDeCadaDiretorBaseadoNumaString(String linha) {
        return Arrays.stream(linha.split(","))
                .map(diretor -> new Diretor(diretor.trim()))
                .collect(Collectors.toList());
    }
	
	public static List<Genero> obterListaContendoCadaGeneroBaseadoNumaString(String linha) {
		return Arrays.stream(linha.split(","))
                .map(genero -> new Genero(genero.trim()))
                .collect(Collectors.toList());
	}
}
