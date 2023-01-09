package br.com.luis.apifilmes.acessoadados;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

import java.util.*;
import org.apache.commons.csv.*;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.arquivo.*;
import br.com.luis.apifilmes.models.enums.*;
import br.com.luis.apifilmes.utils.definidores.*;

public class AcessoADados {
	private Destino destino;
	private LeitorDeCSV leitor;
	private List<Filme> filmes;
	
	public AcessoADados(Destino destino) {
		this.destino = destino;
		leitor = new LeitorDeCSV(destino);
		filmes = new ArrayList<>();
	}

	public AcessoADados() {
		filmes = new ArrayList<>();
	}
	
	public List<Filme> getFilmes() {
		Iterable<CSVRecord> records = leitor.ler();
		
		for (CSVRecord record : records) {
			String titulo = record.get(TITULO.get().trim());
			String data = new DefinidorDeDataEmQueFoiAssistido(destino, record).definir();
			
			Integer ano = record.get(ANO_LANCAMENTO.get()).trim().isEmpty() ? null : Integer.parseInt(record.get(ANO_LANCAMENTO.get()));

			Duracao runtime = new DefinidorDeDuracao(record).definir();

			List<Diretor> diretores = new DefinidorDeListaDeDiretores(record).definir();

			List<Genero> generos = converterStringParaObjeto(Genero::new, record.get(GENERO.get()));
			
			Idioma idioma = record.get(IDIOMA.get()).trim().equals("") ? null : new Idioma(record.get(IDIOMA.get()));
			
			Plataforma plataforma = new DefinidorDePlataforma(destino, record).definir();

			Integer assistidoLegendado = new DefinidorDeAssistidoLegendado(record).definir();

			String linkIMDB = record.get(LINK_IMDB.get());
			String linkImagem = record.get(LINK_IMAGEM.get());
			
			String descricao = record.get(DESCRICAO.get());
			Integer orcamento = new DefinidorDeOrcamento(record).definir();
			List<Ator> atores = new DefinidorDeListaDeAtores(record).definir();
			String classificacao = record.get(CLASSIFICACAO.get());
			List<PaisDeOrigem> paisesDeOrigem = new DefinidorDePaisesDeOrigem(record).definir();

			Filme filme;
			if (destino.ehFilmePendente()) {
				filme = new FilmePendente(titulo, ano, diretores, generos, idioma, runtime, linkIMDB, linkImagem, descricao, orcamento, atores, classificacao, paisesDeOrigem);
			} else {
				filme = new FilmeVisto(titulo, ano, diretores, generos, idioma, runtime, linkIMDB, linkImagem, data, plataforma, assistidoLegendado, descricao, orcamento, atores, classificacao, paisesDeOrigem);
			}
			
			filmes.add(filme);
		}

		return filmes;
	}

	public String[] getDadosDaColuna(Coluna coluna) {
		Iterable<CSVRecord> records = leitor.ler();
		List<String> dadosDaColuna = new ArrayList<>();

		for (CSVRecord record : records) {
			String linha = record.get(coluna.get());
			dadosDaColuna.addAll(converterStringParaObjeto(String::toString, linha.split(",")));
		}

		return converterListaDeStringParaArray(dadosDaColuna);
	}
	
	public String[] getDadosDaColuna(Coluna coluna, Destino destino) {
		Iterable<CSVRecord> records = new LeitorDeCSV(destino).ler();
		List<String> dadosDaColuna = new ArrayList<>();

		for (CSVRecord record : records) {
			String linha = record.get(coluna.get());
			dadosDaColuna.addAll(converterStringParaObjeto(String::toString, linha.split(",")));
		}

		return converterListaDeStringParaArray(dadosDaColuna);
	}

}
