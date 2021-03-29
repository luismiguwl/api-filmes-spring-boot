package br.com.luis.api.arquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	private static String destino = "C:\\Users\\Luis Miguel\\OneDrive\\Documentos do Eclipse\\api-filmes-spring-boot\\filmes-2021\\Filmes assistidos em 2021.csv";

	public static List<String> lerArquivo() {
		List<String> dados = new ArrayList<>();

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
	
	public static List<String> lerArquivo(String destino) {
		List<String> dados = new ArrayList<>();

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
