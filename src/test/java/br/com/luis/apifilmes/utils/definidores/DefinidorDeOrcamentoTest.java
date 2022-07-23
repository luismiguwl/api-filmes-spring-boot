package br.com.luis.apifilmes.utils.definidores;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import br.com.luis.apifilmes.models.enums.*;

@ExtendWith(MockitoExtension.class)
class DefinidorDeOrcamentoTest {

	@Mock
	CSVRecord record;
	
	DefinidorDeOrcamento definidor;
	String coluna = Coluna.ORCAMENTO.get();
	
    @BeforeEach
    void setUp() {
        definidor = new DefinidorDeOrcamento(record);
    }

    @Test
	void deveRetornarOrcamentoQuandoStringNaoForVazia() {
        when(record.get(coluna))
            .thenReturn("100000");

        Assertions.assertEquals(new DefinidorDeOrcamento(record).definir(), 100000);
    }

    @Test
	void deveRetornarNullSeOrcamentoEstiverVazio() {
        when(record.get(coluna))
            .thenReturn("");

        assertNull(new DefinidorDeOrcamento(record).definir());
    }
}
