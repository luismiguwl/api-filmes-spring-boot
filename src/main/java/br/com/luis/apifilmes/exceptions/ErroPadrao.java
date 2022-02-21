package br.com.luis.apifilmes.exceptions;

import java.time.Instant;

public class ErroPadrao {
	
	private Instant timestamp;
	private int status;
	private String erro;
	private String mensagem;
	private String path;
	
	public ErroPadrao() {
	}

	public ErroPadrao(Instant timestamp, int status, String erro, String mensagem, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
		this.mensagem = mensagem;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getErro() {
		return erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getPath() {
		return path;
	}
	
}
