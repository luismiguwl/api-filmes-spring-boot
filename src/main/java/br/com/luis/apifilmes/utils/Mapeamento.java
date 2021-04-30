package br.com.luis.apifilmes.utils;

import static br.com.luis.apifilmes.models.utils.MapeamentoUtils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;

import br.com.luis.apifilmes.models.*;

public class Mapeamento {
    private static Logger logger = LoggerFactory.getLogger(Mapeamento.class);

    public static List<Filme> getFilmes(TipoDeConsulta tipo) {
        List<Filme> filmes = new ArrayList<>();
        String destino = tipo.getDestino();

        try {
            CsvReader csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
            csv.readHeaders();

            while (csv.readRecord()) {
                List<Diretor> diretores = new ArrayList<>();
                Diretor diretor = null;
                
                List<Genero> generos = new ArrayList<>();
                Genero genero = null;

                String titulo = csv.get(Coluna.TITULO.getColuna());
                String data = csv.get(Coluna.DATA_ASSISTIDO.getColuna());
                int ano = Integer.parseInt(csv.get(Coluna.ANO_LANCAMENTO.getColuna()));
                Idioma idioma = new Idioma(csv.get(Coluna.IDIOMA.getColuna()));

                if (csv.get(Coluna.DIRETOR.getColuna()).contains(",")) {
                    diretores = mapearDiretores(csv.get(Coluna.DIRETOR.getColuna()));
                } else {
                    diretor = new Diretor(csv.get(Coluna.DIRETOR.getColuna()));
                }

                int runtime = Integer.parseInt(csv.get(Coluna.DURACAO.getColuna()));
                
                if (csv.get(Coluna.GENERO.getColuna()).contains(",")) {
					generos = mapearGeneros(csv.get(Coluna.GENERO.getColuna()));
				} else {
					genero = new Genero(csv.get(Coluna.GENERO.getColuna()));
				}

                Filme filme = new Filme(titulo, ano, data, idioma, diretor, diretores, genero, generos, runtime);
                filmes.add(filme);
            }
        } catch (IOException e) {
            logger.error("Arquivo não encontrado!");
        }

        return filmes;
    }

    public static List<String> getAbreviacoes() {
        List<String> abreviacoes = new ArrayList<>();
        String destino = TipoDeConsulta.ABREVIACOES.getDestino();

        try {
            CsvReader csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
            csv.readHeaders();

            while (csv.readRecord()) {
                abreviacoes.add(csv.get("idioma") + "," + csv.get("abreviacao"));
            }
        } catch (IOException e) {
            logger.error("Arquivo não encontrado!");
        }

        return abreviacoes;
    }

    public static List<String> getDadosDaColuna(Coluna coluna) {
        List<String> dadosDaColuna = new ArrayList<>();
        String destino = TipoDeConsulta.VISTOS.getDestino();
        String nomeDaColuna = coluna.getColuna();

        try {
            CsvReader csv = new CsvReader(new InputStreamReader(new FileInputStream(destino), "UTF-8"));
            csv.readHeaders();

            while (csv.readRecord()) {
                dadosDaColuna.add(csv.get(nomeDaColuna));
            }
        } catch (IOException e) {
            logger.error("Arquivo não encontrado!");
        }

        return dadosDaColuna;
    }
    
}
