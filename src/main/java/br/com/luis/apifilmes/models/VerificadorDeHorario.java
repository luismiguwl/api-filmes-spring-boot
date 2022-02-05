package br.com.luis.apifilmes.models;

public class VerificadorDeHorario {
    private int horas;
    private int minutos;

    public VerificadorDeHorario(Duracao duracao) {
        this.horas = duracao.getHoras();
        this.minutos = duracao.getMinutos();
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
}
