package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.enums.Destino.*;
import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import br.com.luis.apifilmes.models.enums.Plataforma;

import br.com.luis.apifilmes.models.utils.MapeamentoUtils;
import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.arquivo.*;
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

			String data = null;
			if (!ehFilmePendente()) {
				data = record.get(DATA_ASSISTIDO.get().trim());
			}

			int ano = Integer.parseInt(record.get(ANO_LANCAMENTO.get()).trim());
			int runtimeTipoPrimitivo = Integer.parseInt(record.get(DURACAO.get()).trim());
			
			Duracao runtime = null;
			if (runtimeTipoPrimitivo > 0) {
				runtime = new Duracao(runtimeTipoPrimitivo);
			}
			
			List<Diretor> diretores = obterListaDeObjetosBaseadoNaString(Diretor::new, record.get(DIRETOR.get()));
			String[] nomeDosDiretores = getDadosDaColuna(destino, DIRETOR);
			ContadorDeDiretor contadorDeDiretor = new ContadorDeDiretor();
			diretores = contadorDeDiretor.definirOcorrencias(diretores, nomeDosDiretores);
			
			List<Genero> generos = obterListaDeObjetosBaseadoNaString(Genero::new, record.get(GENERO.get()));
			String[] nomeDosGeneros = getDadosDaColuna(destino, GENERO);
			ContadorDeGenero contadorDeGenero = new ContadorDeGenero();
			generos = contadorDeGenero.definirOcorrencias(generos, nomeDosGeneros);
			
			Idioma idioma = new Idioma(record.get(IDIOMA.get()));
			
			Plataforma plataforma = null;
			boolean assistidoLegendado = false;
			String dataEmQueFoiAdicionado = null;
			if (!ehFilmePendente()) {
				plataforma = definirPlataforma(record.get(PLATAFORMA.get()));
				assistidoLegendado = record.get(ASSISTIDO_LEGENDADO.get()).equals("true");
			} else {
				dataEmQueFoiAdicionado = record.get("dataEmQueFoiAdicionado");
			}

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
	
	public String[] getDadosDaColuna(Destino destino, Coluna... colunas) {
		Iterable<CSVRecord> records = leitor.lerArquivoCsv();
		List<String> dadosDaColuna = new ArrayList<>();

		for (CSVRecord record : records) {
			String dado;
			
			if (colunas.length > 1) {
				dado = obterLinhaContendoDadosDasColunasInserindoVirgulaEntreElesSeForMaisDeUmaColuna(record, colunas);
			} else {
				String coluna = colunas[0].get();
				dado = record.get(coluna);
			}
			
			dadosDaColuna.add(dado);
		}

		String[] dados = MapeamentoUtils.converterListaDeStringParaArray(dadosDaColuna);
		return dados;
	}
	
	private String obterLinhaContendoDadosDasColunasInserindoVirgulaEntreElesSeForMaisDeUmaColuna(CSVRecord record, Coluna[] colunas) {
		String[] dados = new String[colunas.length];
		
		for (int i = 0; i < colunas.length; i++) {
			String coluna = colunas[i].get();
			dados[i] = record.get(coluna);
		}

		return aplicarVirgulaSeNecessario(dados);
	}
	
	private String aplicarVirgulaSeNecessario(String[] dados) {
		if (dados.length > 1) {
			return Arrays.asList(dados).stream()
					.collect(Collectors.joining(","));
		}
		
		return dados[0];
	}

	private Plataforma definirPlataforma(String texto) {
		return Plataforma.valueOfPersonalizado(texto);
	}

}
