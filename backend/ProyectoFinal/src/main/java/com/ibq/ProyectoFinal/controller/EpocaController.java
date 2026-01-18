package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.service.EpocaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EpocaController {

    private final EpocaService epocaService;

    @Autowired
    public EpocaController(EpocaService epocaService) {
        this.epocaService = epocaService;
    }

    // ========== OPERACIÓN CREATE ==========
    @PostMapping("/epocas")
    public ResponseEntity<EpocaDTO> saveEpoca(@Valid @RequestBody EpocaDTO epocaDTO) {
        EpocaDTO savedEpoca = epocaService.saveEpoca(epocaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEpoca);
    }

    // ========== OPERACIONES READ ==========
    //Buscar epocas por nombre
    @GetMapping("/epocas/nombre")
    public ResponseEntity<EpocaDTO> findEpocaByNombre(@RequestParam String nombre) {
        EpocaDTO epoca = epocaService.findByNombre(nombre);
        return ResponseEntity.ok(epoca);
    }
    //Listar epocas
    @GetMapping("/epocas/listado")
    public ResponseEntity<List<EpocaDTO>> listarEpocas() {
        List<EpocaDTO> epocas = epocaService.listarEpocas();
        return ResponseEntity.ok(epocas);
    }

    // ========== OPERACIÓN UPDATE ==========
    @PutMapping("/epocas/{id}")
    public ResponseEntity<EpocaDTO> updateEpoca(
            @RequestBody EpocaDTO epocaDTO,
            @PathVariable("id") Long idEpoca) {
        EpocaDTO updatedEpoca = epocaService.updateEpoca(epocaDTO, idEpoca);
        return ResponseEntity.ok(updatedEpoca);
    }

    // ========== OPERACIÓN DELETE ==========
    @DeleteMapping("/epocas/{id}")
    public ResponseEntity<String> deleteEpoca(@PathVariable("id") Long idEpoca) {
        try {
            epocaService.deleteEpocaById(idEpoca);
            return ResponseEntity.ok("Época eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}