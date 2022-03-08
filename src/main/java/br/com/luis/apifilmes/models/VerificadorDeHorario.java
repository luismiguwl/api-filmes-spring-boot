package br.com.luis.apifilmes.models;

public class VerificadorDeHorario {
    private int horas;
    private int minutos;
    private Duracao duracao;

    public VerificadorDeHorario(Duracao duracao) {
        this.duracao = duracao;
        extrairValoresDaDuracao();
    }
    
    public void setDuracao(Duracao duracao) {
    	this.duracao = duracao;
    	extrairValoresDaDuracao();
    }

    private void extrairValoresDaDuracao() {
    	horas = duracao.getHoras();
    	minutos = duracao.getMinutos();
    }
    
    public boolean possuiApenasUmMinuto() {
        return minutos == 1 && horas == 0;
    }

    public boolean possuiMenosDeUmaHora() {
        return minutos >= 1 && horas == 0;
    }

    public boolean possuiHoraExata() {
        return horas >= 1 && minutos == 0;
    }
    
    public boolean possuiZeroHorasEMinutos() {
    	return horas == 0 && minutos == 0;
    }
}
