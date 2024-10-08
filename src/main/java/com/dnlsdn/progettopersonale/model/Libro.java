package com.dnlsdn.progettopersonale.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titolo;
    @NotNull
    private String testo;
    @NotNull
    private int voto;

    @ManyToOne
    private Scrittore scrittore;

    @OneToMany(mappedBy = "libro")
    private List<Votazione> votazioni;

    public Libro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public Scrittore getScrittore() {
        return scrittore;
    }

    public void setScrittore(Scrittore scrittore) {
        this.scrittore = scrittore;
    }
}
