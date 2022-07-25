package br.com.luis.apifilmes.utils.definidores;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.Ator;
import br.com.luis.apifilmes.models.enums.Coluna;
import br.com.luis.apifilmes.models.utils.AcessoADadosUtils;

public class DefinidorDeListaDeAtores {
    private CSVRecord record;
    private String coluna = Coluna.PRINCIPAIS_ATORES.get();

    public DefinidorDeListaDeAtores(CSVRecord record) {
        this.record = record;
    }

    public List<Ator> definir() {
        if (!record.isMapped(coluna)) {
            return null;
        }

        String[] atores = record.get(coluna).split(", ");
        return AcessoADadosUtils.converterStringParaObjeto(Ator::new, atores);
    }
}
