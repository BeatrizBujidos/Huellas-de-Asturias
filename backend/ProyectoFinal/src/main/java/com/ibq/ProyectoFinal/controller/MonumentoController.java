package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.MonumentoDTO;
import com.ibq.ProyectoFinal.service.MonumentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonumentoController {

    private final MonumentoService monumentoService;

    @Autowired
    public MonumentoController(MonumentoService monumentoService) {
        this.monumentoService = monumentoService;
    }

    // ========== OPERACIÓN CREATE ==========
    @PostMapping("/monumentos")
    public ResponseEntity<MonumentoDTO> saveMonumento(@Valid @RequestBody MonumentoDTO monumentoDTO) {
        MonumentoDTO savedMonumento = monumentoService.saveMonumento(monumentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMonumento);
    }

    // ========== OPERACIONES READ ==========
    //Buscar monumentos por epoca
    @GetMapping("/monumentos/epoca/{idEpoca}")
    public ResponseEntity<List<MonumentoDTO>> findMonumentoByEpoca(@PathVariable Long idEpoca) {
        List<MonumentoDTO> monumentos = monumentoService.findByEpocaId(idEpoca);
        return ResponseEntity.ok(monumentos);
    }
    //Listar monumentos
    @GetMapping("/monumentos/listado")
    public ResponseEntity<List<MonumentoDTO>> listarMonumentos() {
        List<MonumentoDTO> monumentos = monumentoService.listarMonumentos();
        return ResponseEntity.ok(monumentos);
    }

    // ========== OPERACIÓN UPDATE ==========
    @PutMapping("/monumentos/{id}")
    public ResponseEntity<MonumentoDTO> updateMonumento(
            @RequestBody MonumentoDTO monumentoDTO,
            @PathVariable("id") Long idMonumento) {
        MonumentoDTO updatedMonumento = monumentoService.updateMonumento(monumentoDTO, idMonumento);
        return ResponseEntity.ok(updatedMonumento);
    }

    // ========== OPERACIÓN DELETE ==========
    @DeleteMapping("/monumentos/{id}")
    public ResponseEntity<String> deleteMonumento(@PathVariable("id") Long idMonumento) {
        try {
            monumentoService.deleteMonumentoById(idMonumento);
            return ResponseEntity.ok("Monumento eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}