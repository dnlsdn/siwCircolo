package com.dnlsdn.progettopersonale.model;
import jakarta.persistence.*;

@Entity
public class Votazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mittenteId;
    @OneToOne
    private Scrittore destinatario;
    @OneToOne
    private Libro libro;
    private int voto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMittenteId() {
        return mittenteId;
    }

    public void setMittenteId(Long mittenteId) {
        this.mittenteId = mittenteId;
    }

    public Scrittore getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Scrittore destinatario) {
        this.destinatario = destinatario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
