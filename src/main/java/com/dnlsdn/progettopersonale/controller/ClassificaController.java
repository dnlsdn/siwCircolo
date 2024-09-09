package com.dnlsdn.progettopersonale.controller;

import com.dnlsdn.progettopersonale.model.Libro;
import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.service.LibroService;
import com.dnlsdn.progettopersonale.service.ScrittoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ClassificaController {
    @Autowired
    private ScrittoreService scrittoreService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/classifica")
    public String classifica(@RequestParam(value = "cognome", required = false) String cognome, Model model) {
        List<Scrittore> scrittori = new ArrayList<>(scrittoreService.findAll());
        scrittori.sort(new Comparator<Scrittore>() {
            @Override
            public int compare(Scrittore o1, Scrittore o2) {
                int votoTotale1 = libroService.sommaVoti(o1.getLibri());
                int votoTotale2 = libroService.sommaVoti(o2.getLibri());
                int diff = votoTotale2 - votoTotale1;
                if (diff == 0) {
                    return o1.getCognome().compareTo(o2.getCognome());
                }
                return diff;
            }
        });

        for (Scrittore scrittore : scrittori) {
            scrittore.setVotoTotale(libroService.sommaVoti(scrittore.getLibri()));
            scrittoreService.save(scrittore);
        }

        if (cognome != null) {
            List<Scrittore> listaNuova = new ArrayList<>();
            for (Scrittore scrittore : scrittori) {
                if (scrittore.getCognome().equals(cognome)) {
                    listaNuova.add(scrittore);
                }
            }
            if (listaNuova.isEmpty()) {
                model.addAttribute("errore", "Nessun Scrittore trovato con questo cognome!");
                return "classifica";
            }
            model.addAttribute("scrittori", listaNuova);
            return "classifica";
        }
        model.addAttribute("scrittori", scrittori);
        return "classifica";
    }
}
