package com.dnlsdn.progettopersonale.model;

import jakarta.persistence.*;

@Entity
public class Credenziali {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;
    @OneToOne(mappedBy = "credenziali")
    private Scrittore scrittore;
    @OneToOne(mappedBy = "credenziali")
    private Utente utente;

    public Credenziali(String username, String password, Ruolo ruolo) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public Credenziali() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Scrittore getScrittore() {
        return scrittore;
    }

    public void setScrittore(Scrittore scrittore) {
        this.scrittore = scrittore;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public enum Ruolo {
        SCRITTORE,
        UTENTE,
        AMMINISTRATORE
    }
}
