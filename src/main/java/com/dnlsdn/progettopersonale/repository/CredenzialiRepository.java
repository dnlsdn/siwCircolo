package com.dnlsdn.progettopersonale.repository;

import com.dnlsdn.progettopersonale.model.Credenziali;
import org.springframework.data.repository.CrudRepository;

public interface CredenzialiRepository extends CrudRepository<Credenziali, Long> {
    public boolean existsByUsername(String username);
}
