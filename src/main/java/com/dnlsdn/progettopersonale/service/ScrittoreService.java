package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.repository.LibroRepository;
import com.dnlsdn.progettopersonale.repository.ScrittoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrittoreService {
    @Autowired
    private ScrittoreRepository scrittoreRepository;

    public void save(Scrittore scrittore) {
        this.scrittoreRepository.save(scrittore);
    }
}
