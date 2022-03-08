package br.com.luis.apifilmes.models;

public class FormatadorDeDuracao {
	private Duracao duracao;

	public FormatadorDeDuracao(Duracao duracao) {
		this.duracao = duracao;
	}

	public String get() {
		VerificadorDeHorario horario = new VerificadorDeHorario(duracao);

		if (horario.possuiZeroHorasEMinutos()) {
			return "0 minutos";
		} else if (horario.possuiApenasUmMinuto()) {
			return "1 minuto";
		} else if (horario.possuiMenosDeUmaHora()) {
			return String.format("%d minutos", duracao.getMinutos());
		} else if (horario.possuiHoraExata()) {
			return String.format("%dh", duracao.getHoras());
		}

		return String.format("%dh %dm", duracao.getHoras(), duracao.getMinutos());
	}
	
	public void setDuracao(Duracao duracao) {
		this.duracao = duracao;
	}
}
