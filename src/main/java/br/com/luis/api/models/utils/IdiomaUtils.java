package br.com.luis.api.models.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;

import br.com.luis.api.arquivo.Arquivo;
import br.com.luis.api.models.Filme;
import br.com.luis.api.models.TipoDeConsulta;
import br.com.luis.api.utils.Mapeamento;

@ImportAutoConfiguration
public class IdiomaUtils {
	private static List<Filme> filmes = Mapeamento.getFilmesVistos();

	public static String getAbreviacao(String idioma) {
		String destino = "C:\\Users\\Luis Miguel\\OneDrive\\Documentos do Eclipse\\api-filmes-spring-boot\\filmes-2021\\Abreviacoes dos idiomas.csv";
		List<String> listaDeAbreviacoes = Arquivo.lerArquivo(TipoDeConsulta.ABREVIACOES);

		return listaDeAbreviacoes.stream().filter(abreviacao -> abreviacao.split(",")[0].equals(idioma))
				.map(abreviacao -> abreviacao.split(",")[1]).collect(Collectors.toList()).get(0);
	}

	private static String getQuantidadeDeFilmes(String idioma) {
		return filmes.stream().filter(filme -> filme.getIdioma().getNome().equalsIgnoreCase(idioma)).count()
				+ " filmes vistos em " + idioma;
	}

	private static List<String> getIdiomasDistintos() {
		return filmes.stream()
				.map(filme -> filme.getIdioma().getNome())
				.distinct()
				.collect(Collectors.toList());
	}

	public static List<String> definirQuantidadeDeFilmesEmDeterminadoIdioma() {
		return getIdiomasDistintos().stream().map(filme -> getQuantidadeDeFilmes(filme)).collect(Collectors.toList());
	}
}