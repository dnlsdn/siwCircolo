package com.dnlsdn.progettopersonale.repository;

import com.dnlsdn.progettopersonale.model.Scrittore;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScrittoreRepository extends CrudRepository<Scrittore, Long> {
    public Scrittore findByCredenzialiUsername(String username);
}
