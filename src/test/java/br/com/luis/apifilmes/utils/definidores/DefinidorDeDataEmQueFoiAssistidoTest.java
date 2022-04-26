package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import br.com.luis.apifilmes.models.enums.*;
import static br.com.luis.apifilmes.models.enums.Destino.*;

@ExtendWith(MockitoExtension.class)
class DefinidorDeDataEmQueFoiAssistidoTest {
	
	@Mock
	CSVRecord record;
	
	String coluna = Coluna.DATA_ASSISTIDO.get();
	Destino destino = PENDENTES;
	DefinidorDeDataEmQueFoiAssistido definidor;

	@BeforeEach
	void setUp() {
		definidor = new DefinidorDeDataEmQueFoiAssistido(destino, record);
	}

	@Test
	void deveRetornarNullCasoSejaDestinoSejaDeFilmesPendentes() {
		assertNull(definidor.definir());
	}

	@Test
	void deveRetornarADataEmQueFoiAssistido() {
		definidor = new DefinidorDeDataEmQueFoiAssistido(VISTOS_EM_2022, record);

		String data = "14/02/2022";
		when(record.isMapped(coluna)).thenReturn(true);
		when(record.get(coluna)).thenReturn(data);
		
		assertEquals(definidor.definir(), data);
		assertNotNull(definidor.definir());
	}

	@Test
	void deveRetornarNuloSeDataEstiverVazia() {
		definidor = new DefinidorDeDataEmQueFoiAssistido(VISTOS_EM_2022, record);
		
		when(record.isMapped(coluna)).thenReturn(true);
		when(record.get(coluna)).thenReturn("");

		assertNull(definidor.definir());
	}
}
