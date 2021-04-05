package br.com.luis.api.arquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.luis.api.models.TipoDeConsulta;

public class Arquivo {
	private static String destino;

	public static List<String> lerArquivo(TipoDeConsulta tipoDeConsulta) {
		List<String> dados = new ArrayList<>();

		destino = tipoDeConsulta.getDestino(tipoDeConsulta);

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));

			String linha = reader.readLine();

			while (linha != null) {
				dados.add(linha);
				linha = reader.readLine();
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("[ERRO]" + e.getMessage());
		}

		return dados;
	}
	
}
