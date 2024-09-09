package com.dnlsdn.progettopersonale.controller;

import com.dnlsdn.progettopersonale.model.Credenziali;
import com.dnlsdn.progettopersonale.model.Utente;
import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.service.CredenzialiService;
import com.dnlsdn.progettopersonale.service.LibroService;
import com.dnlsdn.progettopersonale.service.ScrittoreService;
import com.dnlsdn.progettopersonale.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class RegisterController {

    @Autowired
    private CredenzialiService credenzialiService;

    @Autowired
    private ScrittoreService giocatoreService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LibroService libroService;

    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam(value = "ruolo", required = false) Credenziali.Ruolo ruolo,
                                       Model model) {
        if (ruolo == null) {
            ruolo = Credenziali.Ruolo.SCRITTORE;
        }

        model.addAttribute("ruoli", Credenziali.Ruolo.values());
        model.addAttribute("ruoloSelezionato", ruolo);

        return "register";
    }

    @GetMapping("/aggiorna")
    public String aggiorna(@RequestParam Credenziali.Ruolo ruolo, Model model) {
        model.addAttribute("ruoli", Credenziali.Ruolo.values());
        model.addAttribute("ruoloSelezionato", ruolo);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String ruolo,
                               @RequestParam(required = false) String nome,
                               @RequestParam(required = false) String cognome,
                               @RequestParam(required = false) LocalDate dataNascita,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        Credenziali.Ruolo ruoloEnum = Credenziali.Ruolo.valueOf(ruolo.toUpperCase());
        String encodedPassword = passwordEncoder.encode(password);

        if (credenzialiService.existsByUsername(username)) {
            model.addAttribute("esistente", "Username già esistente");
            return "register";
        }

        try {
            Credenziali credenziali = new Credenziali(username, encodedPassword, ruoloEnum);
            credenzialiService.save(credenziali);

            if (ruoloEnum == Credenziali.Ruolo.SCRITTORE) {
                Scrittore scrittore = new Scrittore();
                scrittore.setNome(nome);
                scrittore.setCognome(cognome);
                scrittore.setDataNascita(dataNascita);
                scrittore.setCredenziali(credenziali);
                giocatoreService.save(scrittore);
            } else if (ruoloEnum == Credenziali.Ruolo.UTENTE) {
                Utente utente = new Utente();
                utente.setNome(nome);
                utente.setCognome(cognome);
                utente.setDataNascita(dataNascita);
                utente.setCredenziali(credenziali);
                utenteService.save(utente);
            }

            redirectAttributes.addFlashAttribute("success", "Credenziali salvate con successo!");

            try {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
                Authentication authentication = authenticationManager.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Autenticazione avvenuta con successo per: " + authentication.getName());
                model.addAttribute("registrato", "Registrazione Completata. Esegui il login per accedere alle risorse");
                return "login";
            } catch (BadCredentialsException e) {
                System.out.println("Errore di autenticazione: " + e.getMessage());
                model.addAttribute("error", "Credenziali errate.");
                return "redirect:/";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Errore durante la registrazione. Riprova.");
            return "register";
        }
    }
}