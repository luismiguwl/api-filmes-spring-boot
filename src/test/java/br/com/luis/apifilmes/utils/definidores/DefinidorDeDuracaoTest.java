package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.luis.apifilmes.models.Duracao;
import br.com.luis.apifilmes.models.enums.Coluna;

@ExtendWith(MockitoExtension.class)
public class DefinidorDeDuracaoTest {
	
	@Mock
	CSVRecord record;

	DefinidorDeDuracao definidor;
	String coluna = Coluna.DURACAO.get();

	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDeDuracao(record);
	}

	@Test
	public void deveRetornarNullSeDuracaoForMenorOuIgualAZero() {
		when(record.get(coluna)).thenReturn("0");
		assertNull(definidor.definir());
	}

	@Test
	public void deveRetornarUmaInstanciaDeDuracaoSeDuracaoForMaiorQueZero() {
		String runtimeString = "222";
		int runtime = Integer.parseInt(runtimeString);
		
		when(record.get(coluna)).thenReturn(runtimeString);
		
		assertEquals(
				definidor.definir().getDuracaoFormatada(),
				new Duracao(runtime).getDuracaoFormatada()
		);
	}
}
