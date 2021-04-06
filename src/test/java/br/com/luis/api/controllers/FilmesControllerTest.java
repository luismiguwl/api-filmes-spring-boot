package br.com.luis.api.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;

import io.restassured.http.ContentType;

@WebMvcTest
public class FilmesControllerTest {

	@Autowired
	private FilmesVistosController filmesVistosController;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.filmesVistosController);
	}

	@Test
	public void deveRetornarSucesso_QuandoBuscarFilmeAleatorio() {
		given()
		    .accept(ContentType.JSON)
		.when()
		     .get("/filmes/vistos/random")
		.then()
		    .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarUltimoFilme() {
		given()
		    .accept(ContentType.JSON)
		.when()
		     .get("/filmes/vistos/last")
		.then()
		    .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarTodosOsFilmes() {
		given()
		    .accept(ContentType.JSON)
		.when()
		     .get("/filmes/vistos/all")
		.then()
		    .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarFilmeFiltrandoPorMes() {
		given()
		    .accept(ContentType.JSON)
		.when()
		     .get("/filmes/vistos/month/m=?")
		.then()
		    .statusCode(HttpStatus.OK.value());
	}
}
