package verifica.cognolato.verifica.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import verifica.cognolato.verifica.domains.Film;
import verifica.cognolato.verifica.domains.FilmForm;
import verifica.cognolato.verifica.servizi.FilmService;



@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ModelAndView showFilms() {
        if (filmService.getCatalogo().isEmpty()) {
            filmService.riempiCatalogo();
        }
        return new ModelAndView("film-list").addObject("catalogo", filmService.getCatalogo());
    }

    @GetMapping("/nuovo")
    public ModelAndView newFilm() {
        return new ModelAndView("film-form").addObject(new FilmForm());
    }

    @PostMapping("/nuovo")
    public ModelAndView handleNewContact(@ModelAttribute FilmForm filmForm, BindingResult br) {

        if (br.hasErrors()) {
            return new ModelAndView("film-form");
        }

        Film film = filmService.salva(filmForm);

        //pattern PRG
        return new ModelAndView("redirect:/film?codice=" + film.getCodice());

    }


    @GetMapping(params = "codice")
    public ModelAndView mostraFilm(@RequestParam("codice") String codiceFilm) {

        Optional<Film> opFilm = filmService.get(codiceFilm);

        if (opFilm.isPresent()) 
            return new ModelAndView("film-detail").addObject("film", opFilm.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trovato");
        
    }
}
