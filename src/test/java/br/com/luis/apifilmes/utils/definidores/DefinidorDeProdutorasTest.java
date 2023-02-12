package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.luis.apifilmes.models.enums.*;

@ExtendWith(MockitoExtension.class)
public class DefinidorDeProdutorasTest {
	
	@Mock
	CSVRecord record;
	
	DefinidorDeProdutoras definidor;
	final String coluna = Coluna.PRODUTORA.get();
	
	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDeProdutoras(record);
	}
	
	@Test
	public void deveRetornarNullSeColunaEstiverVazia() {
		when(record.get(coluna)).thenReturn("");
		assertNull(definidor.definir());
	}

	@Test
	public void deveRetornarNullSeColunaEstiverVaziaPoremComEspacoNaString() {
		when(record.get(coluna)).thenReturn("    ");
		assertNull(definidor.definir());
	}

	@Test
	public void deveRetornarListaContendoUmaProdutoraSeColunaNaoTiverVirgula() {
		when(record.get(coluna)).thenReturn("Warner Bros.");
		assertEquals(definidor.definir().size(), 1);
		assertEquals(definidor.definir().get(0).getNome(), "Warner Bros.");
	}

	@Test
	public void deveRetornarListaContendoUmaProdutoraSeColunaNaoTiverVirgulaAplicandoTrimParaCadaItem() {
		when(record.get(coluna)).thenReturn("   Warner Bros.        ");
		assertEquals(definidor.definir().size(), 1);
		assertEquals(definidor.definir().get(0).getNome(), "Warner Bros.");
	}

	@Test
	public void deveRetornarListaContendoDuasOuMaisProdutorasQuandoColunaPossuirVirgula() {
		when(record.get(coluna)).thenReturn("Warner Bros., A24");
		assertEquals(definidor.definir().size(), 2);
		assertEquals(definidor.definir().get(0).getNome(), "Warner Bros.");
		assertEquals(definidor.definir().get(1).getNome(), "A24");
	}

	@Test
	public void deveRetornarListaContendoDuasOuMaisProdutorasQuandoColunaPossuirVirgulaAplicandoTrimParaCadaItem() {
		when(record.get(coluna)).thenReturn("      Warner Bros., A24     ");
		assertEquals(definidor.definir().size(), 2);
		assertEquals(definidor.definir().get(0).getNome(), "Warner Bros.");
		assertEquals(definidor.definir().get(1).getNome(), "A24");
	}
}
