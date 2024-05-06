package verifica.cognolato.verifica.servizi;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.Data;
import verifica.cognolato.verifica.domains.Film;
import verifica.cognolato.verifica.domains.FilmForm;


@Service
public class FilmService {
    private ArrayList<Film> catalogo = new ArrayList<Film>();

    public void riempiCatalogo(){
        catalogo.add(new Film("film1", "Star Wars: Il ritorno dello Jedi", "fantascienza", 1983, 4));
        catalogo.add(new Film("film2", "Borat", "commedia", 2006, 4));
        catalogo.add(new Film("film3", "The wolf of wallstreet", "commedia", 2013, 5));
        catalogo.add(new Film("film4", "IT", "horro", 1990, 2));
    }

    public ArrayList<Film> getCatalogo(){
        return catalogo;
    }

    public Film salva(FilmForm filmForm){
        Film f = new Film();
        f.setCodice(filmForm.getCodice());
        f.setTitolo(filmForm.getTitolo());
        f.setGenere(filmForm.getGenere());
        f.setAnno(filmForm.getAnno());
        f.setVoto(filmForm.getVoto());
        catalogo.add(f);
        return f;
    }

    public Optional<Film> get(String codice) {
        if (catalogo.isEmpty()) {
            return null;
        } else{
            for (Film f : catalogo) {
                if (f.getCodice().equals(codice)) {
                    return Optional.of(f);
                }
            }
        }
        return null;
    }
    
}
