package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ImagenDTO;
import com.ibq.ProyectoFinal.service.ImagenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ImagenController {

    private final ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    // ========== OPERACIÓN CREATE ==========
    @PostMapping("/imagenes")
    public ResponseEntity<ImagenDTO> saveImagen(@Valid @RequestBody ImagenDTO imagenDTO) {
        ImagenDTO savedImagen = imagenService.saveImagen(imagenDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedImagen);
    }

    // ========== OPERACIONES READ ==========
    //Buscar imagen por URL
    @GetMapping("/imagenes/url")
    public ResponseEntity<ImagenDTO> getImagenByUrl(@RequestParam String url) {
        Optional<ImagenDTO> imagen = imagenService.findByUrl(url);
        return imagen.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Buscar todas las imágenes de una entidad
    @GetMapping("/imagenes/entidad/{tipoEntidad}/{idEntidad}")
    public ResponseEntity<List<ImagenDTO>> getImagenesByEntidad(
            @PathVariable String tipoEntidad,
            @PathVariable Long idEntidad) {
        List<ImagenDTO> imagenes = imagenService.findByEntidad(tipoEntidad, idEntidad);
        return ResponseEntity.ok(imagenes);
    }

    //Obtener imagen principal de una entidad
    @GetMapping("/imagenes/principal/{tipoEntidad}/{idEntidad}")
    public ResponseEntity<ImagenDTO> getImagenPrincipal(
            @PathVariable String tipoEntidad,
            @PathVariable Long idEntidad) {
        Optional<ImagenDTO> imagen = imagenService.findImagenPrincipal(tipoEntidad, idEntidad);
        return imagen.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== OPERACIÓN UPDATE ==========
    @PutMapping("/imagenes/{id}")
    public ResponseEntity<ImagenDTO> updateImagen(
            @RequestBody ImagenDTO imagenDTO,
            @PathVariable("id") Long idImagen) {
        ImagenDTO updatedImagen = imagenService.updateImagen(imagenDTO, idImagen);
        if (updatedImagen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedImagen);
    }

    // ========== OPERACIONES DELETE ==========
    //Eliminar imagen por ID
    @DeleteMapping("/imagenes/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable("id") Long idImagen) {
        try {
            imagenService.deleteImagenById(idImagen);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
    //Eliminar todas las imágenes de una entidad
    @DeleteMapping("/imagenes/entidad/{tipoEntidad}/{idEntidad}")
    public ResponseEntity<String> deleteAllImagenesByEntidad(
            @PathVariable String tipoEntidad,
            @PathVariable Long idEntidad) {
        try {
            imagenService.deleteAllImagenesByEntidad(tipoEntidad, idEntidad);
            return ResponseEntity.ok("Todas las imágenes de la entidad eliminadas");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}