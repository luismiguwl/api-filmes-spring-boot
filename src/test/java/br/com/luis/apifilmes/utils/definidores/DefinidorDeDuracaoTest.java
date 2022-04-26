package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;

@ExtendWith(MockitoExtension.class)
class DefinidorDeDuracaoTest {
	
	@Mock
	CSVRecord record;

	DefinidorDeDuracao definidor;
	String coluna = Coluna.DURACAO.get();

	@BeforeEach
	void setUp() {
		definidor = new DefinidorDeDuracao(record);
	}

	@Test
	void deveRetornarNullSeDuracaoForMenorOuIgualAZero() {
		when(record.get(coluna)).thenReturn("0");
		assertNull(definidor.definir());
	}

	@Test
	void deveRetornarNullSeDuracaoEstiverVazia() {
		when(record.get(coluna)).thenReturn("");
		assertNull(definidor.definir());
	}

	@Test
	void deveRetornarUmaInstanciaDeDuracaoSeDuracaoForMaiorQueZero() {
		String runtimeString = "222";
		int runtime = Integer.parseInt(runtimeString);
		
		when(record.get(coluna)).thenReturn(runtimeString);
		
		assertEquals(
				definidor.definir().getDuracaoFormatada(),
				new Duracao(runtime).getDuracaoFormatada()
		);
	}
}
