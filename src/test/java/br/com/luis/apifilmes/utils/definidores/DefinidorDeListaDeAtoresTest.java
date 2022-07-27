package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.*;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.models.enums.*;

@ExtendWith(MockitoExtension.class)
class DefinidorDeListaDeAtoresTest {
	
	@Mock
	CSVRecord record;

	DefinidorDeListaDeAtores definidor;
	String coluna = Coluna.PRINCIPAIS_ATORES.get();

	@BeforeEach
	void setUp() {
		definidor = new DefinidorDeListaDeAtores(record);
	}

	@Test
	void deveRetornarUmaListaCom3Diretores() {
		when(record.isMapped(coluna)).thenReturn(true);
		when(record.get(coluna)).thenReturn("Wagner Moura, Christian Bale, Robert Pattinson");
		List<Ator> atores = definidor.definir();
        
        assertEquals(atores.get(0).getNome(), "Wagner Moura");
        assertEquals(atores.get(1).getNome(), "Christian Bale");
        assertEquals(atores.get(2).getNome(), "Robert Pattinson");
		assertEquals(atores.size(), 3);
	}

	@Test
	void deveRetornarUmaListaCom1Diretor() {
		when(record.isMapped(coluna)).thenReturn(true);
		when(record.get(coluna)).thenReturn("Wagner Moura");
		List<Ator> atores = definidor.definir();
        
        assertEquals(atores.get(0).getNome(), "Wagner Moura");
		assertEquals(atores.size(), 1);
	}

	@Test
	void deveRetornarListaVaziaQuandoNaoHouverAtores() {
		when(record.isMapped(coluna)).thenReturn(true);
		when(record.get(coluna)).thenReturn("");
		List<Ator> atores = definidor.definir();
		assertEquals(atores.size(), 0);
	}

	@Test
	void deveRetornarNullSeColunaNaoExistir() {
		when(record.isMapped(coluna)).thenReturn(false);
		assertNull(definidor.definir());
	}
}
