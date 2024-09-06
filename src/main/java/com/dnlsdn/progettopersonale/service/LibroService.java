package com.dnlsdn.progettopersonale.service;

import com.dnlsdn.progettopersonale.repository.GiudiceRepository;
import com.dnlsdn.progettopersonale.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
}
