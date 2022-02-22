package br.com.luis.apifilmes.models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import br.com.luis.apifilmes.exceptions.AnoDaRequisicaoContendoLetraException;
import br.com.luis.apifilmes.models.enums.Destino;

public class DestinoAtualTest {

	DestinoAtual destino;
	
	@Test
	public void deveRetornarDestinoDeFilmesVistosEmDoisMilEVinteEUmSeAnoForValido() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/2021/filmes");
		
		assertEquals(destino.getDestino(), Destino.VISTOS_EM_2021);
	}
	
	@Test
	public void deveRetornarDestinoDeFilmesVistosEmDoisMilEVinteEDoisSeAnoForValido() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/2022/filmes");
		
		assertEquals(destino.getDestino(), Destino.VISTOS_EM_2022);
	}
	
	@Test
	public void deveRetornarDestinoDeFilmesPendentesSePathForParaFilmesPendentes() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/pendentes/filmes");
		
		assertEquals(destino.getDestino(), Destino.PENDENTES);
	}
	
	@Test
	public void deveLancarExcecaoSeAnoForInvalido() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/2020/filmes");
		
		assertThrows(IllegalArgumentException.class, () -> {
			destino.getDestino();
		});
	}
	
	@Test
	public void deveLancarExcecaoSeStringDoAnoNaoForUmInteiro() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/stringInvalida/filmes");
		
		assertThrows(AnoDaRequisicaoContendoLetraException.class, () -> {
			destino.getDestino();
		});
	}
	
	@Test
	public void deveRetornarTrueSePathForParaFilmesPendentes() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/pendentes/filmes");
		
		assertTrue(destino.ehFilmePendente());
	}
	
	@Test
	public void deveRetornarFalseSePathNaoForParaFilmesPendentes() {
		PathAtual path = mock(PathAtual.class);
		destino = new DestinoAtual(path);
		
		when(path.get()).thenReturn("/pendentes1/filmes");
		
		assertFalse(destino.ehFilmePendente());
	}
	
}
