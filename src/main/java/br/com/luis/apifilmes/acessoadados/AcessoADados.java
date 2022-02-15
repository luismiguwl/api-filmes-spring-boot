package br.com.luis.apifilmes.acessoadados;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.enums.Destino.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

import java.util.ArrayList;
import java.util.List;

import br.com.luis.apifilmes.utils.definidores.*;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.arquivo.*;
import br.com.luis.apifilmes.interfaces.AcoesComFilmePendente;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;

public class AcessoADados implements AcoesComFilmePendente {
	private Destino destino;
	private LeitorDeCSV leitor;
	private List<Filme> filmes;
	
	public AcessoADados(Destino destino) {
		this.destino = destino;
		leitor = new LeitorDeCSV(destino);
		filmes = new ArrayList<>();
	}
	
	public List<Filme> getFilmes() {
		Iterable<CSVRecord> records = leitor.lerArquivoCsv();
		
		for (CSVRecord record : records) {
			String titulo = record.get(TITULO.get().trim());
			String data = new DefinidorDeDataEmQueFoiAssistido(destino, record).definir();
			
			int ano = Integer.parseInt(record.get(ANO_LANCAMENTO.get()));
			
			Duracao runtime = new DefinidorDeDuracao(record).definir();
			
			List<Diretor> diretores = obterListaDeObjetosBaseadoNaString(Diretor::new, record.get(DIRETOR.get()));
			String[] nomeDosDiretores = getDadosDaColuna(destino, DIRETOR);
			ContadorDeDiretor contadorDeDiretor = new ContadorDeDiretor();
			diretores = contadorDeDiretor.definirOcorrencias(diretores, nomeDosDiretores);
			
			List<Genero> generos = obterListaDeObjetosBaseadoNaString(Genero::new, record.get(GENERO.get()));
			String[] nomeDosGeneros = getDadosDaColuna(destino, GENERO);
			ContadorDeGenero contadorDeGenero = new ContadorDeGenero();
			generos = contadorDeGenero.definirOcorrencias(generos, nomeDosGeneros);
			
			Idioma idioma = new Idioma(record.get(IDIOMA.get()));
			
			Plataforma plataforma = new DefinidorDePlataforma(destino, record).definir();
			Boolean assistidoLegendado = new DefinidorDeAssistidoLegendado(record).definir();
			String dataEmQueFoiAdicionado = new DefinidorDeDataDeAdicao(destino, record).definir();

			Filme filme;
			if (!ehFilmePendente()) {
				filme = new FilmeVisto(titulo, ano, diretores, generos, idioma, runtime, data, plataforma, assistidoLegendado);
			} else {
				filme = new FilmePendente(titulo, ano, diretores, generos, idioma, runtime, dataEmQueFoiAdicionado);
			}
			
			filmes.add(filme);
		}

		return filmes;
	}

	@Override
	public boolean ehFilmePendente() {
		return destino.equals(PENDENTES);
	}
	
	public String[] getDadosDaColuna(Destino destino, Coluna coluna) {
		Iterable<CSVRecord> records = leitor.lerArquivoCsv();
		List<String> dadosDaColuna = new ArrayList<>();

		for (CSVRecord record : records) {
			String dado = record.get(coluna.get());

			if (dado.contains(",")) {
				String[] valores = dado.split(",");

				for (String valor : valores) {
					dadosDaColuna.add(valor.trim());
				}
			} else {
				dadosDaColuna.add(dado);
			}
		}

		String[] dados = converterListaDeStringParaArray(dadosDaColuna);
		return dados;
	}

}
