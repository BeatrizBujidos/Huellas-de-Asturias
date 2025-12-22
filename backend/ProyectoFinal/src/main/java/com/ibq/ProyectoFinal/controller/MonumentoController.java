package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.dto.MonumentoDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.model.Monumento;
import com.ibq.ProyectoFinal.service.MonumentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonumentoController {
    private final MonumentoService monumentoService;
    @Autowired
    public MonumentoController (MonumentoService monumentoService){
        this.monumentoService = monumentoService;
    }
//Operación CREATE
    @PostMapping("/monumentos")
    public Monumento saveMonumento(@Valid @RequestBody Monumento monumento){
        return monumentoService.saveMonumento(monumento);
    }
//Operación UPDATE
    @PutMapping("/monumentos/{id}")
    public Monumento updateMonumento(@RequestBody Monumento monumento,
                                 @PathVariable("id") Long idMonumento){
        return monumentoService.updateMonumento(monumento, idMonumento);
    }
//Operación DELETE
    @DeleteMapping("/monumentos/{id}")
    public String deleteMonumento(@PathVariable("id") Long idMonumento){
        monumentoService.deleteMonumentoById(idMonumento);
        return "Monumento eliminado correctamente";
    }
//Operaciones READ
    @GetMapping("/monumentos/epoca")
    public List<MonumentoDTO> findMonumentoByEpoca(@RequestParam Long idEpoca){
        return monumentoService.findByEpocaId(idEpoca);
    }

    @GetMapping("/monumentos/listado")
    public List<MonumentoDTO> listarMonumentos(){
        return monumentoService.listarMonumentos();
    }
}
