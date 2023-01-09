package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.apache.commons.csv.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.*;

import br.com.luis.apifilmes.models.enums.*;

@ExtendWith(MockitoExtension.class)
public class DefinidorDePaisesDeOrigemTest {

	@Mock
	CSVRecord record;

	DefinidorDePaisesDeOrigem definidor;
	String coluna = Coluna.PAISES_DE_ORIGEM.get();

	@BeforeEach
	void setUp() {
		definidor = new DefinidorDePaisesDeOrigem(record);
	}

	@Test
	void deveRetornarListaContendoApenasUmPaisSeStringNaoPossuirVirgula() {
		when(record.get(coluna)).thenReturn("Brasil");
		assertTrue(definidor.definir().size() == 1);
	}

	@Test
	void deveRetornarListaContendoDoisPaises() {
		when(record.get(coluna)).thenReturn("Brasil, Suiça");
		assertTrue(definidor.definir().size() == 2);
		assertTrue(definidor.definir().get(0).getNome().equals("Brasil"));
		assertTrue(definidor.definir().get(1).getNome().equals("Suiça"));
	}

	@Test
	void deveRemoverEspacosEmBrancoNoNomeDoPais() {
		when(record.get(coluna)).thenReturn("Brasil , Suiça  ");
		assertTrue(definidor.definir().get(0).getNome().equals("Brasil"));
		assertTrue(definidor.definir().get(1).getNome().equals("Suiça"));
	}

	@Test
	void deveRetornarNullSeStringEstiverVazia() {
		when(record.get(coluna)).thenReturn("");
		assertNull(definidor.definir());
	}
}
