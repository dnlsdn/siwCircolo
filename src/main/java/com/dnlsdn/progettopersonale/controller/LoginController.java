package com.dnlsdn.progettopersonale.controller;

import com.dnlsdn.progettopersonale.service.CredenzialiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CredenzialiService credenzialiService;

    @GetMapping("/login")
    public String login(@RequestParam(name = "erroreLogin", required = false) String erroreLogin, Model model) {
        System.out.println("errore: " + erroreLogin);
        if (erroreLogin != null) {
            model.addAttribute("errore", "Credenziali errate. Riprova");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {
        try {
        System.out.println("Username: " + username);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/";
        } catch (BadCredentialsException e) {
            redirectAttributes.addAttribute("erroreLogin", "Credenziali Errate. Riprova!");
            return "login";
        } catch (AuthenticationException e) {
            // Gestisci altri tipi di eccezioni di autenticazione se necessario
            redirectAttributes.addAttribute("erroreLogin", "Errore durante il login. Riprova!");
            return "login";
        }

    }

}
