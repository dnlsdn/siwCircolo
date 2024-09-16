package com.dnlsdn.progettopersonale.controller;

import com.dnlsdn.progettopersonale.Comparator.ComparatoreLibri;
import com.dnlsdn.progettopersonale.model.Credenziali;
import com.dnlsdn.progettopersonale.model.Libro;
import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.model.Votazione;
import com.dnlsdn.progettopersonale.repository.LibroRepository;
import com.dnlsdn.progettopersonale.service.CredenzialiService;
import com.dnlsdn.progettopersonale.service.LibroService;
import com.dnlsdn.progettopersonale.service.ScrittoreService;
import com.dnlsdn.progettopersonale.service.VotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Controller
public class VotazioneController {
    @Autowired
    private LibroService libroService;

    @Autowired
    private VotazioneService votazioneService;
    @Autowired
    private CredenzialiService credenzialiService;
    @Autowired
    private LibroRepository libroRepository;

    @RequestMapping("/votazione")
    public String votazione(@RequestParam(value = "username") String username, @RequestParam(value = "titolo", required = false) String titolo, Model model) {
        List<Libro> libri = new ArrayList<>(libroService.findAll());
        libri.sort(new ComparatoreLibri());
        model.addAttribute("username", username);
        if (titolo != null) {
            List<Libro> listaNuova = new ArrayList<>();
            for (Libro libro : libri) {
                if (libro.getTitolo().equals(titolo)) {
                    listaNuova.add(libro);
                }
            }
            if (listaNuova.isEmpty()) {
                model.addAttribute("errore", "Nessun libro trovato con questo titolo!");
                return "votazione";
            }
            model.addAttribute("libri", listaNuova);
            return "votazione";
        }
        model.addAttribute("libri", libri);
        return "votazione";
    }

    @GetMapping("/votaLibro")
    public String votaLibro(@RequestParam("username") String username, @RequestParam("libroId") Long libroId, Model model, RedirectAttributes redirectAttributes) {
        Libro libro = libroService.findById(libroId);
        List<Libro> libri = libroService.findAll();
        if (username.equals(libro.getScrittore().getCredenziali().getUsername())) {
            model.addAttribute("stessoUtente", "Non puoi votare un libro di cui sei lo scrittore!");
            model.addAttribute("libri", libri);
            model.addAttribute("username", username);
            return "votazione";
        }

        model.addAttribute("libro", libro);
        model.addAttribute("username", username);
        redirectAttributes.addAttribute("votato", "Il tuo voto è stato salvato con successo!");
        return "votaLibro";
    }

    @PostMapping("/inviaVoto")
    public String inviaVoto(@RequestParam(value = "username") String username, @RequestParam("voto") int voto, @RequestParam("libroId") Long libroId, Model model) {
        List<Votazione> votazioni = votazioneService.findAll();
        Long utenteId = credenzialiService.findIdByUsername(username);
        for (Votazione votazione : votazioni) {
            if (Objects.equals(votazione.getMittenteId(), utenteId) && votazione.getLibro().equals(libroService.findById(libroId))) {
                model.addAttribute("votoPassato", "Hai già votato questo testo! Elimina il voto per eseguirne un altro!");
                List<Libro> libri = libroService.findAll();
                libri.sort(new ComparatoreLibri());
                model.addAttribute("username", username);
                model.addAttribute("libri", libri);
                return "votazione";
            }
        }
        Votazione votazione = new Votazione();
        votazione.setMittenteId(utenteId);
        votazione.setDestinatario(libroService.findById(libroId).getScrittore());
        votazione.setLibro(libroService.findById(libroId));
        votazione.setVoto(voto);
        votazioneService.save(votazione);
        Libro libro = libroService.findById(libroId);
        int nuovoVoto = libro.getVoto() + voto;
        libro.setVoto(nuovoVoto);
        libroService.save(libro);
        List<Libro> libri = libroService.findAll();
        libri.sort(new ComparatoreLibri());
        model.addAttribute("username", username);
        model.addAttribute("libri", libri);
        model.addAttribute("votoInviato", "Voto salvato con successo!");
        return "votazione";
    }


    @GetMapping("/eliminaVoto")
    public String eliminaVoto(@RequestParam("username") String username, @RequestParam("libroId") Long libroId, Model model) {
        model.addAttribute("username", username);
        Libro libro = libroService.findById(libroId);
        List<Votazione> votazioni = votazioneService.findAll();
        Long utenteId = credenzialiService.findIdByUsername(username);
        for (Votazione votazione : votazioni) {
            if (Objects.equals(votazione.getMittenteId(), utenteId) && Objects.equals(votazione.getLibro(), libroService.findById(libroId))) {
                int votoSingolo = votazione.getVoto();
                int votoNuovo = libro.getVoto() - votoSingolo;
                libro.setVoto(votoNuovo);
                libroService.save(libro);
                votazioneService.delete(votazione);
                model.addAttribute("votoEliminato", "Il voto è stato eliminato con successo!");
                List<Libro> libri = libroService.findAll();
                libri.sort(new ComparatoreLibri());
                model.addAttribute("libri", libri);
                return "votazione";
            }
        }
        model.addAttribute("nonVotato", "Non hai votato questo libro!");
        List<Libro> libri = libroService.findAll();
        libri.sort(new ComparatoreLibri());
        model.addAttribute("libri", libri);
        return "votazione";
    }
}
