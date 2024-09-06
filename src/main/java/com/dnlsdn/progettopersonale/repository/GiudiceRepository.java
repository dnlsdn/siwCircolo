package com.dnlsdn.progettopersonale.repository;

import com.dnlsdn.progettopersonale.model.Credenziali;
import com.dnlsdn.progettopersonale.model.Giudice;
import org.apache.juli.logging.Log;
import org.springframework.data.repository.CrudRepository;

public interface GiudiceRepository extends CrudRepository<Giudice, Long> {
}
