package br.com.luis.apifilmes.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luis.apifilmes.models.utils.MesUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@AllArgsConstructor
public class Filme {
	private String titulo;
	private int ano;
	private String data;
	private Idioma idioma;
	private String genero;
	private Diretor diretor;
	private List<Diretor> diretores = new ArrayList<>();
	private int runtime;

	@JsonIgnore
	public Mes getMes() {
		return MesUtils.definirDadosDoMes(this);
	}
	
}
