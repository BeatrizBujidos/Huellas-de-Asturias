package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImagenController {
    private final ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService){
        this.imagenService = imagenService;
    }



}
