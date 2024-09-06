package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.model.Giudice;
import com.dnlsdn.progettopersonale.repository.CredenzialiRepository;
import com.dnlsdn.progettopersonale.repository.GiudiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiudiceService {
    @Autowired
    private GiudiceRepository giudiceRepository;

    public void save(Giudice giudice) {
        this.giudiceRepository.save(giudice);
    }
}
