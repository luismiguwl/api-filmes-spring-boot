package br.com.luis.apifilmes.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.com.luis.apifilmes.exceptions.*;
import br.com.luis.apifilmes.models.AnoAtual;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(AnoDaRequisicaoInvalidoException.class)
	public ResponseEntity<ErroPadrao> handlerAnoInvalido(AnoDaRequisicaoInvalidoException e, HttpServletRequest request) {
		ErroPadrao erro = new ErroPadrao(
				Instant.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Ano precisa estar entre 2021 e " + new AnoAtual().get(),
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = AnoDaRequisicaoContendoLetraException.class)
	public ResponseEntity<ErroPadrao> handlerValorDoAnoInvalido(AnoDaRequisicaoContendoLetraException e, HttpServletRequest request) {
		ErroPadrao erro = new ErroPadrao(
				Instant.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Valor do ano inválido",
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
