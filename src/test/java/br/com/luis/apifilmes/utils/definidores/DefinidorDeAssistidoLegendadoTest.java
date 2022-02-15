package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.luis.apifilmes.models.enums.Coluna;

@ExtendWith(MockitoExtension.class)
public class DefinidorDeAssistidoLegendadoTest {

	@Mock
	CSVRecord record;
	
	DefinidorDeAssistidoLegendado definidor;
	String coluna = Coluna.ASSISTIDO_LEGENDADO.get();
	
	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDeAssistidoLegendado(record);
	}
	
	@Test
	public void deveRetornarValorNaoNuloSeColunarExistir() {
		when(record.isMapped(coluna))
			.thenReturn(true);
		
		when(record.get(coluna))
			.thenReturn("true");
		
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
		when(record.isMapped(coluna))
			.thenReturn(true);
		
		when(record.get(coluna))
			.thenReturn("true");
		
		assertTrue(definidor.definir());
	}

	@Test
	public void deveRetornarFalseSeColunaForFalse() {
		when(record.isMapped(coluna))
			.thenReturn(true);
		
		when(record.get(coluna))
			.thenReturn("false");
		
		assertFalse(definidor.definir());
	}
}
