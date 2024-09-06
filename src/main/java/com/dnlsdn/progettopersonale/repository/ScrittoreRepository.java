package com.dnlsdn.progettopersonale.repository;

import com.dnlsdn.progettopersonale.model.Giudice;
import com.dnlsdn.progettopersonale.model.Scrittore;
import org.springframework.data.repository.CrudRepository;

public interface ScrittoreRepository extends CrudRepository<Scrittore, Long> {
}
