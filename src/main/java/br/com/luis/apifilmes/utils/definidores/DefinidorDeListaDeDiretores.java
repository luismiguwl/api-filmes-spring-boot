package br.com.luis.apifilmes.utils.definidores;

import java.util.*;
import org.apache.commons.csv.*;
import br.com.luis.apifilmes.models.*;
import br.com.luis.apifilmes.acessoadados.*;

import static br.com.luis.apifilmes.models.enums.Coluna.*;
import static br.com.luis.apifilmes.models.enums.Destino.*;
import static br.com.luis.apifilmes.models.utils.AcessoADadosUtils.*;

public class DefinidorDeListaDeDiretores {
	private AcessoADados dados;
	private CSVRecord record;

	public DefinidorDeListaDeDiretores(CSVRecord record) {
		this.dados = new AcessoADados();
		this.record = record;
	}

	public List<Diretor> definir() {
		List<Diretor> diretores = converterStringParaObjeto(Diretor::new, record.get(DIRETOR.get()));
		List<String> nomeDosDiretoresDeFilmesVistos = new ArrayList<>();
		nomeDosDiretoresDeFilmesVistos.addAll(Arrays.asList(dados.getDadosDaColuna(DIRETOR, VISTOS_EM_2021)));
		nomeDosDiretoresDeFilmesVistos.addAll(Arrays.asList(dados.getDadosDaColuna(DIRETOR, VISTOS_EM_2022)));

		String[] nomesPendentes = dados.getDadosDaColuna(DIRETOR, PENDENTES);
		String[] nomeVistos = nomeDosDiretoresDeFilmesVistos.toArray(new String[0]);

		return new ContadorDeDiretor().definirOcorrencias(diretores, nomeVistos, nomesPendentes);
	}
}
