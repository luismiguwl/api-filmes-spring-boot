package br.com.luis.apifilmes.models;

import com.fasterxml.jackson.annotation.*;

public class Duracao {
    private int horas;
    private int minutos;

    public Duracao(int minutos) {
    	this.minutos = minutos;
    	
    	if (!validarNumeros()) {
    		throw new IllegalArgumentException("Valor não pode ser negativo");
	}
    	
        definirHoraEMinuto(minutos);
    }

    public Duracao() {
    }
    
    private void definirHoraEMinuto(int minutos) {
        if (minutos >= 60) {
            horas += (minutos / 60);
            this.minutos = (minutos % 60);
        } else {
            this.minutos = minutos;
        }
    }

    public String getDuracaoFormatada() {
        return new FormatadorDeDuracao(this).get();
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
    
    private boolean validarNumeros() {
    	return minutos >= 0;
    }
    
}
