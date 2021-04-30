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
	private int anoDeLancamento;
	private String dataEmQueFoiAssistido;
	private Idioma idioma;
	private Diretor diretor;
	private List<Diretor> diretores = new ArrayList<>();
	private Genero genero;
	private List<Genero> generos = new ArrayList<>();
	private int runtime;

	@JsonIgnore
	public Mes getMes() {
		return MesUtils.definirDadosDoMes(this);
	}

}
