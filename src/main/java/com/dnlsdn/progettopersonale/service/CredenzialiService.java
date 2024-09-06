package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Credenziali;
import com.dnlsdn.progettopersonale.repository.CredenzialiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredenzialiService {
    @Autowired
    private CredenzialiRepository credenzialiRepository;

    public boolean existsByUsername(String username) {
        return credenzialiRepository.existsByUsername(username);
    }

    public void save(Credenziali credenziali) {
        this.credenzialiRepository.save(credenziali);
    }
}
