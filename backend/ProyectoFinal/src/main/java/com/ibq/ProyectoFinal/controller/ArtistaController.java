package com.ibq.ProyectoFinal.controller;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistaController {
    private final ArtistaService artistaService;
    @Autowired
    public ArtistaController (ArtistaService artistaService){
        this.artistaService = artistaService;
    }
//Operación CREATE
    @PostMapping("/artistas")
    public Artista saveArtista(@Valid @RequestBody Artista artista){
        return artistaService.saveArtista(artista);
    }
//Operación UPDATE
    @PutMapping("/artistas/{id}")
    public Artista updateArtista(@RequestBody Artista artista,
                            @PathVariable("id") Long idArtista){
        return artistaService.updateArtista(artista, idArtista);
    }
//Operaciones READ
    @GetMapping("/artistas/nombreYapellidos")
    public ArtistaDTO findArtistaByNombreAndApellidos(@RequestParam String nombre,
                                                      @RequestParam String apellidos){
        return artistaService.findByNombreAndApellidos(nombre, apellidos);
    }
//Listado de artistas por estilo
    @GetMapping("/artistas/estilo")
    public List<ArtistaDTO> findArtistaByEstilo(@RequestParam String estilo){
        return artistaService.findByEstilo(estilo);
    }
    //Operación DELETE
    @DeleteMapping("/artistas/{id}")
    public ResponseEntity<Void>deleteArtistaId(@PathVariable Long idArtista){
        try{
            artistaService.deleteArtistaById(idArtista);
            return ResponseEntity.noContent().build();//204 eliminación exitosa
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();//404 artista no encontrado
        }
    }
}
