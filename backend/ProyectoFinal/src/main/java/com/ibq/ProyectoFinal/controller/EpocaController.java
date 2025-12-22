package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.model.Epoca;
import com.ibq.ProyectoFinal.service.EpocaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EpocaController {
    private final EpocaService epocaService;
    @Autowired
    public EpocaController (EpocaService epocaService){
        this.epocaService = epocaService;
    }
//Operación CREATE
    @PostMapping("/epocas")
    public Epoca saveEpoca(@Valid @RequestBody Epoca epoca){
        return epocaService.saveEpoca(epoca);
    }
//Operación UPDATE
    @PutMapping("/epocas/{id}")
    public Epoca updateEpoca(@RequestBody Epoca epoca,
                                 @PathVariable("id") Long idEpoca){
        return epocaService.updateEpoca(epoca, idEpoca);
    }
//Operación DELETE
    @DeleteMapping("/epocas/{id}")
    public String deleteEpoca(@PathVariable("id") Long idEpoca){
        epocaService.deleteEpocaById(idEpoca);
        return "Época eliminada correctamente";
    }
//Operaciones READ
    @GetMapping("/epocas/nombre")
    public EpocaDTO findEpocaByNombre(@RequestParam String nombre){
        return epocaService.findByNombre(nombre);
    }
//Listado de épocas
    @GetMapping("/epocas/listado")
    public List<EpocaDTO> listarEpocas(){
        return epocaService.listarEpocas();
    }
}
