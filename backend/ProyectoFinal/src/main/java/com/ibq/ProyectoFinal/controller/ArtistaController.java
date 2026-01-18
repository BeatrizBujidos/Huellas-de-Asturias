package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class ArtistaController {
    private final ArtistaService artistaService;
    @Autowired
    public ArtistaController (ArtistaService artistaService){
        this.artistaService = artistaService;
    }
// ========== OPERACIÓN CREATE ==========
    @PostMapping("/artistas")
    public ResponseEntity<ArtistaDTO> saveArtista(@Valid @RequestBody ArtistaDTO artistaDTO) {
        ArtistaDTO savedArtista = artistaService.saveArtista(artistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtista);
    }

    // ========== OPERACIONES READ ==========
    //Buscar artistas por nombre y apellidos
    @GetMapping("/artistas/nombreYapellidos")
    public ResponseEntity<ArtistaDTO> findArtistaByNombreAndApellidos(
            @RequestParam String nombre,
            @RequestParam String apellidos) {
        ArtistaDTO artista = artistaService.findByNombreAndApellidos(nombre, apellidos);
        return ResponseEntity.ok(artista);
    }
    //Buscar artistas por estilo
    @GetMapping("/artistas/estilo")
    public ResponseEntity<List<ArtistaDTO>> findArtistaByEstilo(@RequestParam String estilo) {
        List<ArtistaDTO> artistas = artistaService.findByEstilo(estilo);
        return ResponseEntity.ok(artistas);
    }

    // ========== OPERACIÓN UPDATE ==========
    @PutMapping("/artistas/{id}")
    public ResponseEntity<ArtistaDTO> updateArtista(
            @RequestBody ArtistaDTO artistaDTO,
            @PathVariable("id") Long idArtista) {
        ArtistaDTO updatedArtista = artistaService.updateArtista(artistaDTO, idArtista);
        return ResponseEntity.ok(updatedArtista);
    }

    // ========== OPERACIÓN DELETE ==========
    @DeleteMapping("/artistas/{id}")
    public ResponseEntity<Void> deleteArtistaId(@PathVariable("id") Long idArtista) {
        try {
            artistaService.deleteArtistaById(idArtista);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}