package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import br.com.luis.apifilmes.models.enums.*;

@ExtendWith(MockitoExtension.class)
public class DefinidorDeAssistidoLegendadoTest {

	@Mock
	CSVRecord record;
	
	DefinidorDeAssistidoLegendado definidor;
	String coluna = Coluna.ASSISTIDO_LEGENDADO.get();
	
	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDeAssistidoLegendado(record);
		when(record.isMapped(coluna))
			.thenReturn(true);
	}
	
	@Test
	public void deveRetornarValorNaoNuloSeColunarExistir() {
		when(record.get(coluna))
			.thenReturn("1");
		
		assertNotNull(definidor.definir());
	}
	
	@Test
	public void deveRetonarNullSeColunaNaoExistir() {
		when(record.isMapped(coluna))
			.thenReturn(false);

		assertNull(definidor.definir());
	}

	@Test
	public void deveRetornarTrueSeColunaForTrue() {
		when(record.get(coluna))
		.thenReturn("1");
		
		assertEquals(definidor.definir(), Integer.valueOf(1));
	}

	@Test
	public void deveRetornarFalseSeColunaForFalse() {
		when(record.get(coluna))
		.thenReturn("0");
		
		assertEquals(definidor.definir(), Integer.valueOf(0));
	}

	@Test
	public void deveRetornarNullSeValorForUmaStringVazia() {
		when(record.get(coluna))
		.thenReturn("");
		
		assertNull(definidor.definir());
	}
}
