package br.com.luis.apifilmes.models.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.Diretor;

public class MapeamentoUtils {
	public static List<Diretor> mapearDiretores(String linha) {
        return Arrays.stream(linha.split(","))
                .map(diretor -> new Diretor(diretor.trim()))
                .collect(Collectors.toList());
    }
}
