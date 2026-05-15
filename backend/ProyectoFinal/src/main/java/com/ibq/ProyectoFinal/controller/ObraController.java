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
    // Operacion CREATE
    @PostMapping("/obras")
    public ResponseEntity<ObraDTO> saveObra(@Valid @RequestBody ObraDTO obraDTO) {
        ObraDTO savedObra = obraService.saveObra(obraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedObra);
    }
    // Operaciones READ
    //Buscar obras por id
    @GetMapping("/obras/{id}")
    public ResponseEntity<ObraDTO> findById(@PathVariable Long id){
        ObraDTO obra = obraService.findById(id);
        return ResponseEntity.ok(obra);
    }
    //Buscar obras por titulo
    @GetMapping("/obras/titulo/{titulo}")
    public ResponseEntity<ObraDTO> findObrasPorTitulo(@PathVariable String titulo) {
        ObraDTO obra = obraService.findByTitulo(titulo);
        return ResponseEntity.ok(obra);
    }
    //Buscar obras por tecnica
    @GetMapping("/obras/tecnica/{tecnica}")
    public ResponseEntity<List<ObraDTO>> findObrasPorTecnica(@PathVariable String tecnica) {
        List<ObraDTO> obras = obraService.findByTecnica(tecnica);
        return ResponseEntity.ok(obras);
    }
    // Buscar obras por nombre de ARTISTA
    @GetMapping("/obras/artista/nombre/{nombreArtista}")
    public ResponseEntity<List<ObraDTO>> findObrasPorArtistaNombre(@PathVariable String nombreArtista) {
        List<ObraDTO> obras = obraService.findByArtistaNombre(nombreArtista);
        return ResponseEntity.ok(obras);
    }
    // Buscar obras por nombre de MUSEO
    @GetMapping("/obras/museo/nombre/{nombreMuseo}")
    public ResponseEntity<List<ObraDTO>> findObrasPorMuseoNombre(@PathVariable String nombreMuseo) {
        List<ObraDTO> obras = obraService.findByMuseoNombre(nombreMuseo);
        return ResponseEntity.ok(obras);
    }
    // Buscar obras por nombre de EPOCA
    @GetMapping("/obras/epoca/nombre/{nombreEpoca}")
    public ResponseEntity<List<ObraDTO>> findObrasPorEpocaNombre(@PathVariable String nombreEpoca) {
        List<ObraDTO> obras = obraService.findByEpocaNombre(nombreEpoca);
        return ResponseEntity.ok(obras);
    }
    //Listar todas las obras
    @GetMapping("/obras")
    public ResponseEntity<List<ObraDTO>> listAll(){
        List<ObraDTO> obras = obraService.listAll();
        return ResponseEntity.ok(obras);
    }
    // Operacion UPDATE
    @PutMapping("/obras/{id}")
    public ResponseEntity<ObraDTO> updateObra(@RequestBody ObraDTO obraDTO, @PathVariable("id") Long idObra) {
        ObraDTO updatedObra = obraService.updateObra(obraDTO, idObra);
        return ResponseEntity.ok(updatedObra);
    }
    // Operacion DELETE
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