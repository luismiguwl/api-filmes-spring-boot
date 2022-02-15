package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.luis.apifilmes.models.enums.*;
import static br.com.luis.apifilmes.models.enums.Destino.*;

@ExtendWith(MockitoExtension.class)
public class DefinidorDeDataEmQueFoiAssistidoTest {
	
	@Mock
	CSVRecord record;
	
	DefinidorDeDataEmQueFoiAssistido definidor;
	String coluna = Coluna.DATA_ASSISTIDO.get();
	Destino destino = PENDENTES;
	
	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDeDataEmQueFoiAssistido(destino, record);
	}

	@Test
	public void deveRetornarNullCasoSejaDestinoSejaDeFilmesPendentes() {
		assertNull(definidor.definir());
	}

	@Test
	public void deveRetornarADataEmQueFoiAssistido() {
		definidor = new DefinidorDeDataEmQueFoiAssistido(VISTOS_EM_2022, record);

		String data = "14/02/2022";
		when(record.get(coluna)).thenReturn(data);
		
		assertEquals(definidor.definir(), data);
	}
}
