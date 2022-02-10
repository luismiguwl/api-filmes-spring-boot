package br.com.luis.apifilmes.models;

import java.util.Calendar;

public class AnoAtual {
	public int get() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
