package com.dnlsdn.progettopersonale.repository;

import com.dnlsdn.progettopersonale.model.Libro;
import com.dnlsdn.progettopersonale.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {
}
