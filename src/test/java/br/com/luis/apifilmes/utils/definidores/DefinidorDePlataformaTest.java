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
public class DefinidorDePlataformaTest {
	
	@Mock
	CSVRecord record;
	
	DefinidorDePlataforma definidor;
	Destino destino = Destino.VISTOS_EM_2022;
	String coluna = Coluna.PLATAFORMA.get();
	
	@BeforeEach
	public void setUp() {
		definidor = new DefinidorDePlataforma(destino, record);
	}
	
	@Test
	public void deveRetornarNullSeForFilmePendente() {
		definidor = new DefinidorDePlataforma(Destino.PENDENTES, record);
		assertNull(definidor.definir());
	}
	
	@Test
	public void deveRetornarPlataforma() {
		when(record.get(coluna)).thenReturn("Netflix");
		assertEquals(definidor.definir(), Plataforma.NETFLIX);
	}
}
