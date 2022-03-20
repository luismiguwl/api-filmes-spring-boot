package br.com.luis.apifilmes.arquivo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import br.com.luis.apifilmes.models.enums.*;

class LeitorDeCSVTest {
	
	Destino destino = Destino.VISTOS_EM_2022;
	LeitorDeCSV leitor = new LeitorDeCSV(destino);
	
	@Test
	void deveRetornarUmIteravel() {
		assertNotNull(leitor.ler());
		assertTrue(leitor.ler() instanceof Iterable);
	}
	
	@Test
	void deveRetornarNuloSeDestinoForNulo() {
		leitor.setDestino(null);
		assertNull(leitor.ler());
	}
	
	@Test
	void deveRetornarNuloSeDestinoNaoExistir() {
		destino = mock(Destino.class);
		leitor.setDestino(destino);
		
		when(destino.get()).thenReturn("");
		
		assertNull(leitor.ler());
	}
}
	