package br.com.luis.apifilmes.arquivo;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.interfaces.AcoesComFilmePendente;
import br.com.luis.apifilmes.models.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

public class PreenchedorDeCampos implements AcoesComFilmePendente {
	private Filme filme;
	private List<Object> campos = new ArrayList<>();
	private Joiner joiner = new Joiner();

	public PreenchedorDeCampos(Filme filme) {
		this.filme = filme;
	}

	public List<Object> preencher() {
		if (ehFilmePendente()) {
			preencherParaFilmePendente();
		} else {
			preencherParaFilmeVisto();
		}
		
		return campos;
	}

	@Override
	public boolean ehFilmePendente() {
		return filme instanceof FilmePendente;
	}
	
	private void preencherParaFilmePendente() {
		FilmePendente filme = (FilmePendente) this.filme;
		
		campos.add(filme.getTitulo());
		campos.add(Integer.toString(filme.getAnoDeLancamento()));
		campos.add(filme.getIdioma().getNome());
		campos.add(joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome));
		campos.add(joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome));
		campos.add(filme.getRuntime() != null ? filme.getRuntime().getDuracaoEmMinutos() : 0);
		campos.add(filme.getDataEmQueFoiAdicionado() != null ? filme.getDataEmQueFoiAdicionado() : "");
	}
	
	private void preencherParaFilmeVisto() {
		FilmeVisto filme = (FilmeVisto) this.filme;
		
		campos.add(filme.getTitulo());
		campos.add(filme.getDataEmQueFoiAssistido());
		campos.add(Integer.toString(filme.getAnoDeLancamento()));
		campos.add(filme.getIdioma().getNome());
		campos.add(joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getDiretores(), Diretor::getNome));
		campos.add(joiner.getDadosSeparadosPorVirgulaSeNecessario(filme.getGeneros(), Genero::getNome));
		
		campos.add(filme.getRuntime().getDuracaoEmMinutos());
		campos.add(filme.getPlataformaEmQueFoiAssistido().getNome());
		campos.add(filme.getAssistidoLegendado());
	}
}
