package br.com.luis.api.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.api.models.Filme;
import br.com.luis.api.utils.Mapeamento;

@RestController
@RequestMapping("/filmes/pendentes")
public class FilmesPendentesControllers {

	private List<Filme> filmes = Mapeamento.getFilmesPendentes();
	
	@GetMapping("/random")
	public Filme random() {
		int posicaoAleatoria = new Random().nextInt(filmes.size());
		return filmes.get(posicaoAleatoria);
	}
	
	@GetMapping("/all")
	public List<Filme> listarTodos() {
		return filmes;
	}
}
