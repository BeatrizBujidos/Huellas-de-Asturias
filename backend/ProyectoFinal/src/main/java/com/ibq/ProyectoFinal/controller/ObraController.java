package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.service.ObraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ObraController {

    private final ObraService obraService;

    @Autowired
    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    // ========== OPERACIÓN CREATE ==========
    @PostMapping("/obras")
    public ResponseEntity<ObraDTO> saveObra(@Valid @RequestBody ObraDTO obraDTO) {
        ObraDTO savedObra = obraService.saveObra(obraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedObra);
    }
    // ========== OPERACIONES READ ==========
    //Buscar obras por titulo
    @GetMapping("/obras/titulo/{titulo}")
    public ResponseEntity<ObraDTO> buscarObrasPorTitulo(@PathVariable String titulo) {
        ObraDTO obra = obraService.findByTitulo(titulo);
        return ResponseEntity.ok(obra);
    }
    //Buscar obras por artista
    @GetMapping("/obras/artista/{artistaId}")
    public ResponseEntity<List<ObraDTO>> buscarObrasPorArtista(@PathVariable Long artistaId) {
        List<ObraDTO> obras = obraService.findByArtistaId(artistaId);
        return ResponseEntity.ok(obras);
    }
    //Buscar obras por técnica
    @GetMapping("/obras/tecnica/{tecnica}")
    public ResponseEntity<List<ObraDTO>> buscarObrasPorTecnica(@PathVariable String tecnica) {
        List<ObraDTO> obras = obraService.findByTecnica(tecnica);
        return ResponseEntity.ok(obras);
    }
    //Buscar obras por epoca
    @GetMapping("/obras/epoca/{epocaId}")
    public ResponseEntity<List<ObraDTO>> buscarObrasPorEpoca(@PathVariable Long epocaId) {
        List<ObraDTO> obras = obraService.findByEpocaId(epocaId);
        return ResponseEntity.ok(obras);
    }
    //Buscar obras por museo
    @GetMapping("/obras/museo/{museoId}")
    public ResponseEntity<List<ObraDTO>> buscarObrasPorMuseo(@PathVariable Long museoId) {
        List<ObraDTO> obras = obraService.findByMuseoId(museoId);
        return ResponseEntity.ok(obras);
    }

    // ========== OPERACIÓN UPDATE ==========
    @PutMapping("/obras/{id}")
    public ResponseEntity<ObraDTO> updateObra(
            @RequestBody ObraDTO obraDTO,
            @PathVariable("id") Long idObra) {
        ObraDTO updatedObra = obraService.updateObra(obraDTO, idObra);
        return ResponseEntity.ok(updatedObra);
    }

    // ========== OPERACIÓN DELETE ==========
    @DeleteMapping("/obras/{id}")
    public ResponseEntity<String> deleteObra(@PathVariable("id") Long idObra) {
        try {
            obraService.deleteObraById(idObra);
            return ResponseEntity.ok("Obra eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}