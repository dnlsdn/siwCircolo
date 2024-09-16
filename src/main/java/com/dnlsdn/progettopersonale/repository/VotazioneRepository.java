package com.dnlsdn.progettopersonale.repository;

import com.dnlsdn.progettopersonale.model.Credenziali;
import com.dnlsdn.progettopersonale.model.Votazione;
import org.springframework.data.repository.CrudRepository;

public interface VotazioneRepository extends CrudRepository<Votazione, Long> {
}
