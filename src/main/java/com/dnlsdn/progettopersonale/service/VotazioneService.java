package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Credenziali;
import com.dnlsdn.progettopersonale.model.Votazione;
import com.dnlsdn.progettopersonale.repository.CredenzialiRepository;
import com.dnlsdn.progettopersonale.repository.VotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotazioneService {
    @Autowired
    private VotazioneRepository votazioneRepository;

    public List<Votazione> findAll() {
        return (List<Votazione>) this.votazioneRepository.findAll();
    }

    public void save(Votazione votazione) {
        this.votazioneRepository.save(votazione);
    }

    public void delete(Votazione votazione) {
        this.votazioneRepository.delete(votazione);
    }
}
