package br.com.luis.apifilmes.models;

import java.util.List;

public class FilmePendente extends Filme {
	public FilmePendente(String titulo, Integer anoDeLancamento, List<Diretor> diretores, List<Genero> generos,
			Idioma idioma, Duracao runtime, String linkIMDB, String linkImagem, String descricao, Integer orcamento, List<Ator> atores, String classificacao, List<PaisDeOrigem> paisesDeOrigem, List<Produtora> produtoras) {
		super(titulo, anoDeLancamento, diretores, generos, idioma, runtime, linkIMDB, linkImagem, descricao, orcamento, atores, classificacao, paisesDeOrigem, produtoras);
	}

	public FilmePendente() {
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
	public Duracao getRuntime() {
		return super.getRuntime();
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
	public String getClassificacao() {
		return super.getClassificacao();
	}
	
	@Override
	public void setClassificacao(String classificacao) {
		super.setClassificacao(classificacao);
	}

	@Override
	public List<PaisDeOrigem> getPaisesDeOrigem() {
		return super.getPaisesDeOrigem();
	}

	@Override
	public List<Produtora> getProdutoras() {
		return super.getProdutoras();
	}

	@Override
	public void setProdutoras(List<Produtora> produtoras) {
		super.setProdutoras(produtoras);
	}

	@Override
	public void setPaisesDeOrigem(List<PaisDeOrigem> paisesDeOrigem) {
		super.setPaisesDeOrigem(paisesDeOrigem);
	}

	@Override
	public String mesclarTituloComDiretores() {
		return super.mesclarTituloComDiretores();
	}

}
