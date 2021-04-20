package br.com.luis.apifilmes.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mes {
	private String nome;
	private int numeroDoMes; 
}