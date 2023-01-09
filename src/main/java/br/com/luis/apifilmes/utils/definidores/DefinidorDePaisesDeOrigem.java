package br.com.luis.apifilmes.utils.definidores;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import br.com.luis.apifilmes.models.PaisDeOrigem;
import br.com.luis.apifilmes.models.enums.Coluna;

public class DefinidorDePaisesDeOrigem {
    private CSVRecord record;
    private String coluna = Coluna.PAISES_DE_ORIGEM.get();
    
    public DefinidorDePaisesDeOrigem(CSVRecord record) {
        this.record = record;
    }

    public List<PaisDeOrigem> definir() {
        List<PaisDeOrigem> paises = new ArrayList<>();

        String paisesSeparadosPorVirgula = this.record.get(coluna);

        if (paisesSeparadosPorVirgula.isEmpty() || paisesSeparadosPorVirgula.isBlank()) {
            return null;
        }

        for (String pais : paisesSeparadosPorVirgula.split(",")) {
            paises.add(new PaisDeOrigem(pais.trim()));
        }

        return paises;
    }
}
