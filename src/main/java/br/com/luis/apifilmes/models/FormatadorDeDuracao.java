package br.com.luis.apifilmes.models;

public class FormatadorDeDuracao {
	private Duracao duracao;

	public FormatadorDeDuracao(Duracao duracao) {
		this.duracao = duracao;
	}

	public String get() {
		VerificadorDeHorario validador = new VerificadorDeHorario(duracao);

		if (validador.possuiApenasUmMinuto()) {
			return "1 minuto";
		} else if (validador.possuiMenosDeUmaHora()) {
			return String.format("%d minutos", duracao.getMinutos());
		} else if (validador.possuiHoraExata()) {
			return String.format("%dh", duracao.getHoras());
		}

		return String.format("%dh %dm", duracao.getHoras(), duracao.getMinutos());
	}
}
