package verifica.cognolato.verifica.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private String codice;
    private String titolo;
    private String genere;
    private int anno;
    private int voto;
}
