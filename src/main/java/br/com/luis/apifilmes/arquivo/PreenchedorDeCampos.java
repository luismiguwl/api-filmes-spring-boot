package br.com.luis.apifilmes.arquivo;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.interfaces.AcoesComFilmePendente;
import br.com.luis.apifilmes.models.*;
import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

public class PreenchedorDeCampos implements AcoesComFilmePendente {
	private Filme filme;
	private List<Object> campos = new ArrayList<>();

	public PreenchedorDeCampos(Filme filme) {
		this.filme = filme;
	}

	public List<Object> prencher() {
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
		String[] diretores = obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, filme.getDiretores());
		campos.add(diretores.length > 0 ? String.join(", ", diretores) : diretores[0].trim());
		String[] generos = obterArrayDeStringContendoAtributoDeUmaClasse(Genero::getNome, filme.getGeneros());
		campos.add(generos.length > 0 ? String.join(", ", generos) : generos[0].trim());
		
		if (filme.getRuntime() != null) {
			campos.add(filme.getRuntime());
		}
	}
	
	private void preencherParaFilmeVisto() {
		FilmeVisto filme = (FilmeVisto) this.filme;
		
		campos.add(filme.getTitulo());
		campos.add(filme.getDataEmQueFoiAssistido());
		campos.add(Integer.toString(filme.getAnoDeLancamento()));
		campos.add(filme.getIdioma().getNome());
		String[] diretores = obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, filme.getDiretores());
		campos.add(diretores.length > 0 ? String.join(", ", diretores) : diretores[0].trim());
		String[] generos = obterArrayDeStringContendoAtributoDeUmaClasse(Genero::getNome, filme.getGeneros());
		campos.add(generos.length > 0 ? String.join(", ", generos) : generos[0].trim());
		
		campos.add(Integer.toString(filme.getRuntime().getDuracaoEmMinutos()));
		campos.add(filme.getPlataformaEmQueFoiAssistido().getNome());
		campos.add(filme.getAssistidoLegendado());
	}
}
