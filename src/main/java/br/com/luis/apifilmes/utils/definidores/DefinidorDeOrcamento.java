package br.com.luis.apifilmes.utils.definidores;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.enums.Coluna;

public class DefinidorDeOrcamento {
    private String orcamento;
    
    public DefinidorDeOrcamento(CSVRecord record) {
        this.orcamento = record.get(Coluna.ORCAMENTO.get());
    }

    public Integer definir() {
        return orcamento.equals("") ? null : Integer.parseInt(orcamento);
    }
}
