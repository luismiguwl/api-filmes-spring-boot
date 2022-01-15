package br.com.luis.apifilmes.models;

public class Idioma {
    private String nome;

    public Idioma() {
	}
    
    public Idioma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
