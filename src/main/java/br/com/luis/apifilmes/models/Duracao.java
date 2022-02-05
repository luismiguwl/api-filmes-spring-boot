package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Duracao {
    private int horas;
    private int minutos;

    public Duracao(int horas, int minutos) {
        this.horas = horas;
        definirMinuto(minutos);
    }

    public void definirMinuto(int minutos) {
        if (minutos >= 60) {
            horas += (minutos / 60);
            this.minutos = (minutos % 60);
        } else {
            this.minutos = minutos;
        }
    }

    public Duracao(int minutos) {
        definirHoraEMinuto(minutos);
    }

    public void definirHoraEMinuto(int minutos) {
        if (minutos < 60) {
            this.minutos = minutos;
        } else {
            horas = minutos / 60;
            this.minutos = (minutos % 60);
        }
    }

    public String getDuracaoFormatada() {
        VerificadorDeHorario validador = new VerificadorDeHorario(this);
        
        if (validador.possuiApenasUmMinuto()) {
            return "1 minuto";
        } else if (validador.possuiMenosDeUmaHora()) {
            return String.format("%d minutos", minutos);
        } else if (validador.possuiHoraExata()) {
            return String.format("%dh", horas);
        }

        return String.format("%dh %dm", horas, minutos);
    }

    public int getDuracaoEmMinutos() {
        return (horas * 60) + minutos;
    }

    @JsonIgnore
    public int getHoras() {
        return horas;
    }
    
    @JsonIgnore
    public int getMinutos() {
        return minutos;
    }

}
