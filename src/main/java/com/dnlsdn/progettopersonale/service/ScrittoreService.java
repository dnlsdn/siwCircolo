package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Scrittore;
import com.dnlsdn.progettopersonale.repository.ScrittoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScrittoreService {
    @Autowired
    private ScrittoreRepository scrittoreRepository;

    public void save(Scrittore scrittore) {
        this.scrittoreRepository.save(scrittore);
    }

    public List<Scrittore> findAll() {
        return (List<Scrittore>) this.scrittoreRepository.findAll();
    }

    public Scrittore findByCredenzialiUsername(String username) {
        return this.scrittoreRepository.findByCredenzialiUsername(username);
    }

    public Scrittore findById(Long id) {
        return this.scrittoreRepository.findById(id).orElse(null);
    }
}
