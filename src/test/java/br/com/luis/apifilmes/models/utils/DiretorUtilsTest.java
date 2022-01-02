package br.com.luis.apifilmes.models.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.Diretor;
import br.com.luis.apifilmes.utils.Calculadora;

class DiretorUtilsTest {

	List<Diretor> diretores;
	Calculadora calc = Calculadora.get();
	
	@Test
	@DisplayName("Deve retornar linha contendo o nome dos diretores separado por espaço")
	void mesclarTodosOsDiretoresTest() {
		Diretor d1 = new Diretor("Christopher Nolan");
		Diretor d2 = new Diretor("Jay Oliva");
		
		String linhaEsperada = "Christopher Nolan Jay Oliva";
		String linhaRetornada = DiretorUtils.mesclarTodosOsDiretores(List.of(d1, d2));
		
		Assertions.assertThat(linhaRetornada).isEqualTo(linhaEsperada);
	}
	
	@Test
	@DisplayName("Deve retornar um valor inteiro maior que 0 se o diretor existir na lista")
	void getQuantidadeDeFilmesVistosTest1() {
		Diretor diretorAlvo = diretores.get(calc.getNumeroAleatorio(diretores.size()));
		
		String[] nomeDosDiretores = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, diretores);;
		
		int quantidadeDeFilmes = DiretorUtils.getQuantidadeDeFilmesVistos(nomeDosDiretores, diretorAlvo);
		assertThat(quantidadeDeFilmes).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("Deve retornar 0 se diretor não existir na lista")
	void getQuantidadeDeFilmesVistosTest2() {
		Diretor diretorAlvo = new Diretor("Diretor que não está na lista");
		
		String[] nomeDosDiretores = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, diretores);
		
		int quantidadeDeFilmes = DiretorUtils.getQuantidadeDeFilmesVistos(nomeDosDiretores, diretorAlvo);
		assertThat(quantidadeDeFilmes).isEqualTo(0);
	}
	
	@Test
	void filtrarDiretoresComMaisFilmesTest() {
		int top = 3;
		String[] nomeDosDiretores = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, diretores);
		
		List<Diretor> diretoresComMaisFilmesVistos = DiretorUtils.filtrarDiretoresComMaisFilmes(nomeDosDiretores, top);

		String[] diretoresRecebidos = MapeamentoUtils.obterArrayDeStringContendoAtributoDeUmaClasse(Diretor::getNome, diretoresComMaisFilmesVistos);
		String[] diretoresEsperados = {"Christopher Nolan", "Pierre Coffin", "Jon Favreau"};

		assertThat(diretoresEsperados).isEqualTo(diretoresRecebidos);
	}
	
	@BeforeEach
	void getListaDeDiretores() {
		Diretor d1 = new Diretor("Christopher Nolan");
		Diretor d2 = new Diretor("Christopher Nolan");
		Diretor d3 = new Diretor("Christopher Nolan");
		Diretor d4 = new Diretor("Chris Palmer");
		Diretor d5 = new Diretor("Chris Palmer");
		Diretor d6 = new Diretor("Jon Favreau");
		Diretor d7 = new Diretor("Jon Favreau");
		Diretor d8 = new Diretor("Pierre Coffin");
		
		diretores = List.of(d1, d2, d3, d4, d5, d6, d7, d8);
	}

}
