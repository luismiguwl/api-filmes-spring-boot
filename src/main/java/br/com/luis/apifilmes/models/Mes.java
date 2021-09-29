package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static br.com.luis.apifilmes.utils.Mapeamento.getDadosDaColuna;

public class Mes {
	private String nome;
	
	@JsonIgnore
	private int numeroDoMes;
	
	public Mes(String nome) {
		this.nome = nome;
	}
	
	public Mes(String nome, int numeroDoMes) {
		this.nome = nome;
		this.numeroDoMes = numeroDoMes;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getNumeroDoMes() {
		return numeroDoMes;
	}
	
	public int getQuantidadeDeFilmesVistosNoMes() {
		String[] datas = getDadosDaColuna(Coluna.DATA_ASSISTIDO);
		int quantidadeDeFilmesVistosNoMes = 0;
		
		// 15/02/2021
		// String[] d = {"15", "02", "2021"};
		// int mes = d[1];

		for (String data : datas) {
			String[] dadosDaDataSeparadosPorBarra = data.split("/");
			int mes = Integer.parseInt(dadosDaDataSeparadosPorBarra[1]);

			if (mes == getNumeroDoMes()) {
				quantidadeDeFilmesVistosNoMes++;
			}
		}

		return quantidadeDeFilmesVistosNoMes;
	}	
	
}