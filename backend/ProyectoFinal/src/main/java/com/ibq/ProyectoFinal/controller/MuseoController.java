package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.MuseoDTO;
import com.ibq.ProyectoFinal.service.MuseoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MuseoController {

    private final MuseoService museoService;

    @Autowired
    public MuseoController(MuseoService museoService) {
        this.museoService = museoService;
    }

    // ========== OPERACIÓN CREATE ==========
    @PostMapping("/museos")
    public ResponseEntity<MuseoDTO> saveMuseo(@Valid @RequestBody MuseoDTO museoDTO) {
        MuseoDTO savedMuseo = museoService.saveMuseo(museoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMuseo);
    }

    // ========== OPERACIONES READ ==========
    //Buscar museos por ciudad
    @GetMapping("/museos/{ciudad}")
    public ResponseEntity<List<MuseoDTO>> buscarMuseosPorCiudad(@PathVariable String ciudad) {
        List<MuseoDTO> museos = museoService.findByCiudad(ciudad);
        return ResponseEntity.ok(museos);
    }
    //Listar museos
    @GetMapping("/museos/listado")
    public ResponseEntity<List<MuseoDTO>> listarMuseos() {
        List<MuseoDTO> museos = museoService.listarMuseos();
        return ResponseEntity.ok(museos);
    }

    // ========== OPERACIÓN UPDATE ==========
    @PutMapping("/museos/{id}")
    public ResponseEntity<MuseoDTO> updateMuseo(
            @RequestBody MuseoDTO museoDTO,
            @PathVariable("id") Long idMuseo) {
        MuseoDTO updatedMuseo = museoService.updateMuseo(museoDTO, idMuseo);
        return ResponseEntity.ok(updatedMuseo);
    }

    // ========== OPERACIÓN DELETE ==========
    @DeleteMapping("/museos/{id}")
    public ResponseEntity<String> deleteMuseo(@PathVariable("id") Long idMuseo) {
        try {
            museoService.deleteMuseoById(idMuseo);
            return ResponseEntity.ok("Museo eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}