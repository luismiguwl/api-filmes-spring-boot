package br.com.luis.apifilmes.models;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luis.apifilmes.models.utils.DiretorUtils;
import br.com.luis.apifilmes.models.utils.GeneroUtils;
import br.com.luis.apifilmes.models.utils.MesUtils;
import br.com.luis.apifilmes.utils.Mapeamento;

public class AnaliseDosFilmes {
	private int minutosAssistidos;
	private int totalEmHoras;
	private int totalEmDias;
	private int diretoresDiferentes;
	private int generosDiferentes;
	private Diretor diretorComMaisFilmesVistos;
	private Mes mesEmQueMaisFilmesForamAssistidos;
	private Genero generoMaisAssistido;
	private List<Filme> filmes;
	private Filme primeiroFilmeVisto;
	private Filme ultimoFilmeVisto;
	
	public AnaliseDosFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public int getMinutosAssistidos() {
		minutosAssistidos = filmes.stream()
				.map(filme -> filme.getRuntime())
				.reduce((total, duracao) -> total + duracao)
				.get();

		return minutosAssistidos;
	}

	public int getTotalEmHoras() {
		totalEmHoras = minutosAssistidos / 60;
		return totalEmHoras;
	}

	public int getTotalEmDias() {
		return totalEmHoras / 24;
	}

	public int getDiretoresDiferentes() {
		List<String> nomeDosDiretores = Mapeamento.getDadosDaColuna(Coluna.DIRETOR);
		List<Diretor> diretores = DiretorUtils.mapearDiretores(nomeDosDiretores);

		return (int) diretores.stream()
				.map(diretor -> diretor.getNome())
				.distinct()
				.count();
	}

	public int getGenerosDiferentes() {
		List<String> nomeDosGeneros = Mapeamento.getDadosDaColuna(Coluna.GENERO);
		nomeDosGeneros = GeneroUtils.obterCadaGenero(nomeDosGeneros);

		return (int) nomeDosGeneros.stream()
				.distinct()
				.count();
	}

	public Diretor getDiretorComMaisFilmesVistos() {
		return DiretorUtils.filtrarDiretoresComMaisFilmes(filmes, 1).get(0);
	}

	public Mes getMesEmQueMaisFilmesForamAssistidos() {
		List<Mes> meses = filmes.stream()
				.map(filme -> filme.getMes())
				.collect(Collectors.toList());
		
		meses = MesUtils.getListaDeMesesOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(meses);
		
		return meses.stream()
				.collect(Collectors.toList())
				.get(0);
	}
	
	public Genero getGeneroMaisAssistido() {
		List<Genero> generos = GeneroUtils.getAllGeneros(filmes);
		generos = GeneroUtils.getListaDeGenerosOrdenadasPorQuantidadeDeFilmesDeFormaDecrescente(generos);
		
		Genero generoMaisAssistido = generos.get(0);
		
		return generoMaisAssistido;
	}
	
	public Filme getPrimeiroFilmeVisto() {
		return filmes.get(0);
	}
	
	public Filme getUltimoFilmeVisto() {
		int lastMoviePosition = filmes.size() - 1;
		return filmes.get(lastMoviePosition);
	}
}
