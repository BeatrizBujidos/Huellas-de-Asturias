package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.MuseoDTO;
import com.ibq.ProyectoFinal.model.Museo;
import com.ibq.ProyectoFinal.service.MuseoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MuseoController {
    private final MuseoService museoService;
    @Autowired
    public MuseoController (MuseoService museoService){
        this.museoService = museoService;
    }
//Operación CREATE
    @PostMapping("/museos")
    public Museo saveMuseo(@Valid @RequestBody Museo museo){
        return museoService.saveMuseo(museo);
    }
//Operación UPDATE
    @PutMapping("/museos/{id}")
    public Museo updateMuseo(@RequestBody Museo museo,
                                 @PathVariable("id") Long idMuseo){
        return museoService.updateMuseo(museo, idMuseo);
    }
//Operación DELETE
    @DeleteMapping("/museos/{id}")
    public String deleteMuseo(@PathVariable("id") Long idMuseo){
        museoService.deleteMuseoById(idMuseo);
        return "Museo eliminado correctamente";
    }
//Operaciones READ
    @GetMapping("/museos/{ciudad}")
    public List<MuseoDTO> buscarMuseosPorCiudad(@PathVariable String ciudad){
        return museoService.findByCiudad(ciudad);
    }

    @GetMapping("/museos/listado")
    public List<MuseoDTO> listarMuseos(){
        return museoService.listarMuseos();
    }
}
