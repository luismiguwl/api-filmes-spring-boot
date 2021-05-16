package br.com.luis.apifilmes.models;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luis.apifilmes.utils.Mapeamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mes {
	private String nome;
	
	@JsonIgnore
	private int numeroDoMes;
	
	public int getQuantidadeDeFilmesVistosNoMes() {
		List<String> datas = Mapeamento.getDadosDaColuna(Coluna.DATA_ASSISTIDO);
		List<Integer> numeroDosMeses = datas.stream()
				.map(data -> Integer.parseInt(data.split("/")[1]))
				.collect(Collectors.toList());
		
		return (int) numeroDosMeses.stream()
				.filter(numero -> numero == numeroDoMes)
				.count();
	}
}