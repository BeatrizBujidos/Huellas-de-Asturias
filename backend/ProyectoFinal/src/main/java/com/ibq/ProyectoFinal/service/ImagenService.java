package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenService {
    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenService (ImagenRepository imagenRepository){
        this.imagenRepository = imagenRepository;
    }
}
