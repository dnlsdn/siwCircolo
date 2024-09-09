package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Libro;
import com.dnlsdn.progettopersonale.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public void save(Libro libro) {
         this.libroRepository.save(libro);
    }

    public List<Libro> findAll() {
        return (List<Libro>) this.libroRepository.findAll();
    }


    public int sommaVoti(List<Libro> libri) {
        int somma = 0;
        for (Libro libro : libri) {
            somma += libro.getVoto();
        }
        return somma;
    }

    public Libro findById(Long id) {
        return this.libroRepository.findById(id).get();
    }
}
