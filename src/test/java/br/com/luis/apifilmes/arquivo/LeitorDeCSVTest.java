package br.com.luis.apifilmes.arquivo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.models.enums.Destino;

public class LeitorDeCSVTest {
	
	Destino destino;
	LeitorDeCSV leitor;
	
	@BeforeEach
	public void setUp() {
		destino = Destino.VISTOS_EM_2022;
		leitor = new LeitorDeCSV(destino);
	}
	
	@Test
	public void deveRetornarUmIteravel() {
		assertNotNull(leitor.ler());
	}
	
	@Test
	public void deveRetornarNuloSeDestinoForNulo() {
		leitor = new LeitorDeCSV(null);
		assertNull(leitor.ler());
	}
	
	@Test
	public void deveRetornarNuloSeNaoEncontrarArquivo() {
		destino = mock(Destino.class);
		leitor = new LeitorDeCSV(destino);
		
		when(destino.get()).thenReturn("");
		
		assertNull(leitor.ler());
	}
}
	