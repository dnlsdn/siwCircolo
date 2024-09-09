package com.dnlsdn.progettopersonale.controller;

import com.dnlsdn.progettopersonale.model.Libro;
import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.service.LibroService;
import com.dnlsdn.progettopersonale.service.ScrittoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequestMapping("/scrittore")
@Controller
public class NuovoTestoController {
    @Autowired
    private ScrittoreService scrittoreService;
    @Autowired
    private LibroService libroService;

    @GetMapping("/nuovoTesto")
    public String nuovoTesto(@RequestParam("username") String username, Model model) {
        Scrittore scrittore = scrittoreService.findByCredenzialiUsername(username);
        model.addAttribute("scrittoreId", scrittore.getId());
        return "nuovoTesto";
    }

    @PostMapping("/testoAggiunto")
    public String testoAggiunto(@RequestParam("titolo") String titolo,
                                @RequestParam("testo") String testo,
                                @RequestParam("scrittoreId") Long scrittoreId,
                                RedirectAttributes redirectAttributes) {
        Libro libro = new Libro();
        Scrittore scrittore = scrittoreService.findById(scrittoreId);
        libro.setTitolo(titolo);
        libro.setTesto(testo);
        libro.setVoto(0);
        libro.setScrittore(scrittore);
        scrittore.getLibri().add(libro);
        libroService.save(libro);
        redirectAttributes.addFlashAttribute("testoAggiunto", "Testo Aggiunto con successo!");
        return "redirect:/";
    }
}
