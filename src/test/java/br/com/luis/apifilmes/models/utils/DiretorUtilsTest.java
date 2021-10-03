package br.com.luis.apifilmes.models.utils;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.Diretor;

class DiretorUtilsTest {

	@Test
	void test() {
		Diretor d1 = new Diretor("Christopher Nolan");
		Diretor d2 = new Diretor("Jay Oliva");
		
		String linhaEsperada = "Christopher Nolan Jay Oliva";
		String linhaRetornada = DiretorUtils.mesclarTodosOsDiretores(List.of(d1, d2));
		
		Assertions.assertThat(linhaRetornada).isEqualTo(linhaEsperada);
	}

}
