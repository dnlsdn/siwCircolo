package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Utente;
import com.dnlsdn.progettopersonale.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public void save(Utente utente) {
        this.utenteRepository.save(utente);
    }
}
