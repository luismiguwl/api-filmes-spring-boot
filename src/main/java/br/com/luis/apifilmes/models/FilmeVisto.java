package br.com.luis.apifilmes.models;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

import br.com.luis.apifilmes.models.enums.Plataforma;

public class FilmeVisto extends Filme {
	private String dataEmQueFoiAssistido;
	private Plataforma plataformaEmQueFoiAssistido;
	private Integer assistidoLegendado;

	public FilmeVisto(String titulo, Integer anoDeLancamento, List<Diretor> diretores, List<Genero> generos, Idioma idioma,
			Duracao runtime, String linkIMDB, String linkImagem, String dataEmQueFoiAssistido, Plataforma plataformaEmQueFoiAssistido,
			Integer assistidoLegendado, String descricao, Integer orcamento, List<Ator> atores) {
		super(titulo, anoDeLancamento, diretores, generos, idioma, runtime, linkIMDB, linkImagem, descricao, orcamento, atores);
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
		this.plataformaEmQueFoiAssistido = plataformaEmQueFoiAssistido;
		this.assistidoLegendado = assistidoLegendado;
	}

	public FilmeVisto() {
	}

	@JsonProperty(value = "assistidoEm")
	public String getDataEmQueFoiAssistido() {
		return dataEmQueFoiAssistido;
	}

	public void setDataEmQueFoiAssistido(String dataEmQueFoiAssistido) {
		this.dataEmQueFoiAssistido = dataEmQueFoiAssistido;
	}

	public Plataforma getPlataformaEmQueFoiAssistido() {
		return plataformaEmQueFoiAssistido;
	}

	public void setPlataformaEmQueFoiAssistido(Plataforma plataformaEmQueFoiAssistido) {
		this.plataformaEmQueFoiAssistido = plataformaEmQueFoiAssistido;
	}

	public Integer getAssistidoLegendado() {
		return assistidoLegendado;
	}

	public void setAssistidoLegendado(int assistidoLegendado) {
		this.assistidoLegendado = assistidoLegendado;
	}
	
	@Override
	public Duracao getRuntime() {
		return super.getRuntime();
	}

	@Override
	public String getTitulo() {
		return super.getTitulo();
	}

	@Override
	public Integer getAnoDeLancamento() {
		return super.getAnoDeLancamento();
	}

	@Override
	public List<Diretor> getDiretores() {
		return super.getDiretores();
	}
	
	@Override
	public Idioma getIdioma() {
		return super.getIdioma();
	}

	@Override
	public List<Genero> getGeneros() {
		return super.getGeneros();
	}
	
	@Override
	public String getLinkIMDB() {
		return super.getLinkIMDB();
	}
	
	@Override
	public String getLinkImagem() {
		return super.getLinkImagem();
	}

	@Override
	public String getDescricao() {
		return super.getDescricao();
	}

	@Override
	public void setDescricao(String descricao) {
		super.setDescricao(descricao);
	}

	@Override
	public Integer getOrcamento() {
		return super.getOrcamento();
	}

	@Override
	public void setOrcamento(Integer orcamento) {
		super.setOrcamento(orcamento);
	}

	@Override
	public List<Ator> getAtores() {
		return super.getAtores();
	}

	@Override
	public void setAtores(List<Ator> atores) {
		super.setAtores(atores);
	}

	@Override
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

}
