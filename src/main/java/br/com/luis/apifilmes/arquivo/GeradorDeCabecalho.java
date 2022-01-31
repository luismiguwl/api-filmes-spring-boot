package br.com.luis.apifilmes.arquivo;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.models.*;
import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.converterListaDeStringParaArray;

import static br.com.luis.apifilmes.models.enums.Coluna.*;

public class GeradorDeCabecalho implements AcoesComFilmePendente {
	private Filme filme;
	private List<String> headers;

	public GeradorDeCabecalho(Filme filme) {
		this.filme = filme;
		this.headers = new ArrayList<>();
	}
	
	public String[] gerar() {
		if (ehFilmePendente()) {
			gerarHeadersParaFilmePendente();
		} else {
			gerarHeadersParaFilmeVisto();
		}
		
		return converterListaDeStringParaArray(headers);
	}

	@Override
	public boolean ehFilmePendente() {
		return filme instanceof FilmePendente;
	}
	
	private void gerarHeadersParaFilmeVisto() {
		headers.add(TITULO.get());
		headers.add(DATA_ASSISTIDO.get());
		headers.add(ANO_LANCAMENTO.get());
		headers.add(IDIOMA.get());
		headers.add(DIRETOR.get());
		headers.add(GENERO.get());
		headers.add(DURACAO.get());
		headers.add(PLATAFORMA.get());
		headers.add(ASSISTIDO_LEGENDADO.get());
	}

	private void gerarHeadersParaFilmePendente() {
		headers.add(TITULO.get());
		headers.add(ANO_LANCAMENTO.get());
		headers.add(IDIOMA.get());
		headers.add(DIRETOR.get());
		headers.add(GENERO.get());
		headers.add(DURACAO.get());
	}

}