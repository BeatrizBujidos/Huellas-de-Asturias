package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.model.Obra;
import com.ibq.ProyectoFinal.service.ObraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ObraController {
    private final ObraService obraService;
    @Autowired
    public ObraController (ObraService obraService){
        this.obraService = obraService;
    }
//Operación CREATE
    @PostMapping("/obras")
    public Obra saveObra(@Valid @RequestBody Obra obra){
        return obraService.saveObra(obra);
    }
    //Operación UPDATE
    @PutMapping("/obras/{id}")
    public Obra updateObra(@RequestBody Obra obra,
                             @PathVariable("id") Long idObra){
        return obraService.updateObra(obra, idObra);
    }
    //Operación DELETE
    @DeleteMapping("/obras/{id}")
    public String deleteObra(@PathVariable("id") Long idObra){
        obraService.deleteObraById(idObra);
        return "Obra eliminada correctamente";
    }
    //Operaciones READ
    //Buscar obras por título
    @GetMapping("/obras/{titulo}")
    public Obra buscarObrasPorTitulo(@PathVariable String titulo){
        return obraService.findByTitulo(titulo);
    }//Listar obras por artista
    @GetMapping("/obras/{artista}")
    public List<ObraDTO> buscarObrasPorArtista(@PathVariable Long artistaId){
        return obraService.findByArtistaId(artistaId);
    }
    //Listar obras por técnica
    @GetMapping("/obras/{tecnica}")
    public List<ObraDTO> buscarObrasPorTecnica(@PathVariable String tecnica){
        return obraService.findByTecnica(tecnica);
    }
    //Listar obras por época
    @GetMapping("/obras/{epoca}")
    public List<ObraDTO> buscarObrasPorEpoca(@PathVariable Long epocaId){
        return obraService.findByEpocaId(epocaId);
    }
    //Listar obras por museo
    @GetMapping("/obras/{museo}")
    public List<ObraDTO> buscarObrasPorMuseo(@PathVariable Long idMuseo){
        return obraService.findByMuseoId(idMuseo);
    }
}
