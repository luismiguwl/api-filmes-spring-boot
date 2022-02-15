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
public class DefinidorDeDataDeAdicaoTest {
	
	@Mock
	CSVRecord record;
	
	DefinidorDeDataDeAdicao definidor;
	String coluna = Coluna.DATA_DE_ADICAO.get();
	Destino destino = PENDENTES;
	
	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDeDataDeAdicao(destino, record);
	}

	@Test
	public void deveRetornarNullSeNaoForFilmePendente() {
		definidor = new DefinidorDeDataDeAdicao(VISTOS_EM_2022, record);
		assertNull(definidor.definir());	
	}

	@Test
	public void deveRetornarNullSeColunaNaoPossuirDataDeAdicao() {
		when(record.get(coluna)).thenReturn("".repeat(5));
		assertNull(definidor.definir());	
	}

	@Test
	public void deveRetornarOValorContidoNaColuna() {
		String data = "14/02/2022";
		when(record.get(coluna)).thenReturn(data);
		assertEquals(definidor.definir(), data);	
	}
	
}
