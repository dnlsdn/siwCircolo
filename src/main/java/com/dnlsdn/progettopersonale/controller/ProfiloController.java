package com.dnlsdn.progettopersonale.controller;

import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.model.Utente;
import com.dnlsdn.progettopersonale.service.ScrittoreService;
import com.dnlsdn.progettopersonale.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfiloController {
    @Autowired
    private ScrittoreService scrittoreService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/profilo")
    public String profilo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String ruolo = null;
        String username = auth.getName();

        for (GrantedAuthority authority : auth.getAuthorities()) {
            ruolo = authority.getAuthority();
        }

        model.addAttribute("ruolo", ruolo);
        if ("UTENTE".equals(ruolo)) {
            System.out.println("Username: " + username);
            Utente utente = utenteService.findByCredenzialiUsername(username);
            model.addAttribute("user", utente);
        } else if ("SCRITTORE".equals(ruolo)) {
            Scrittore scrittore = scrittoreService.findByCredenzialiUsername(username);
            model.addAttribute("user", scrittore);
        }
        return "profilo";
    }
}
