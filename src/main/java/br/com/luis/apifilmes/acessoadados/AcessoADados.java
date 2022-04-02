package br.com.luis.apifilmes.acessoadados;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

import java.util.*;
import br.com.luis.apifilmes.utils.definidores.*;
import org.apache.commons.csv.CSVRecord;
import br.com.luis.apifilmes.arquivo.*;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;

public class AcessoADados {
	private Destino destino;
	private LeitorDeCSV leitor;
	private List<Filme> filmes;
	
	public AcessoADados(Destino destino) {
		this.destino = destino;
		leitor = new LeitorDeCSV(destino);
		filmes = new ArrayList<>();
	}
	
	public List<Filme> getFilmes() {
		Iterable<CSVRecord> records = leitor.ler();
		
		for (CSVRecord record : records) {
			String titulo = record.get(TITULO.get().trim());
			String data = new DefinidorDeDataEmQueFoiAssistido(destino, record).definir();
			
			int ano = Integer.parseInt(record.get(ANO_LANCAMENTO.get()));
			
			Duracao runtime = new DefinidorDeDuracao(record).definir();
			
			List<Diretor> diretores = converterStringParaObjeto(Diretor::new, record.get(DIRETOR.get()));
			List<String> nomeDosDiretoresDeFilmesVistos = new ArrayList<>();
			nomeDosDiretoresDeFilmesVistos.addAll(Arrays.asList(getDadosDaColuna(DIRETOR, Destino.VISTOS_EM_2021)));
			nomeDosDiretoresDeFilmesVistos.addAll(Arrays.asList(getDadosDaColuna(DIRETOR, Destino.VISTOS_EM_2022)));
			String[] nomeDosDiretoresDeFilmesPendentes = getDadosDaColuna(DIRETOR, Destino.PENDENTES);
			ContadorDeDiretor contadorDeDiretor = new ContadorDeDiretor();
			diretores = contadorDeDiretor.definirOcorrencias(diretores, nomeDosDiretoresDeFilmesVistos.toArray(new String[0]), nomeDosDiretoresDeFilmesPendentes);
			
			List<Genero> generos = converterStringParaObjeto(Genero::new, record.get(GENERO.get()));
			String[] nomeDosGeneros = getDadosDaColuna(GENERO);
			ContadorDeGenero contadorDeGenero = new ContadorDeGenero();
			generos = contadorDeGenero.definirOcorrencias(generos, nomeDosGeneros);
			
			Idioma idioma = new Idioma(record.get(IDIOMA.get()));
			
			Plataforma plataforma = new DefinidorDePlataforma(destino, record).definir();
			Boolean assistidoLegendado = new DefinidorDeAssistidoLegendado(record).definir();
			String dataEmQueFoiAdicionado = new DefinidorDeDataDeAdicao(destino, record).definir();

			Filme filme;
			if (!destino.ehFilmePendente()) {
				filme = new FilmeVisto(titulo, ano, diretores, generos, idioma, runtime, data, plataforma, assistidoLegendado);
			} else {
				filme = new FilmePendente(titulo, ano, diretores, generos, idioma, runtime, dataEmQueFoiAdicionado);
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
