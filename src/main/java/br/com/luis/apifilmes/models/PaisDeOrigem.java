package br.com.luis.apifilmes.models;

public class PaisDeOrigem {
    private String nome;

    public PaisDeOrigem(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
