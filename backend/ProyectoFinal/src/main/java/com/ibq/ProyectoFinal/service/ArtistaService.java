package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.repository.ArtistaRepository;
import com.ibq.ProyectoFinal.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArtistaService {
    private final ArtistaRepository artistaRepository;
    private final ObraRepository obraRepository;

    @Autowired
    public ArtistaService (ArtistaRepository artistaRepository, ObraRepository obraRepository){
        this.artistaRepository = artistaRepository;
        this.obraRepository = obraRepository;
    }
    // ========== MAPPERS ==========

    //mapToDTO
    private ArtistaDTO mapToDTO(Artista artista) {
        return ArtistaDTO.builder()
                .nombre(artista.getNombre())
                .apellidos(artista.getApellidos())
                .fechaNacimiento(artista.getFechaNacimiento())
                .fechaMuerte(artista.getFechaMuerte())
                .lugarNacimiento(artista.getLugarNacimiento())
                .biografia(artista.getBiografia())
                .estilo(artista.getEstilo())
                .imagen(artista.getImagen())
                .build();
    }

    //DTO → Entity
    private Artista mapToEntity(ArtistaDTO dto) {
        Artista artista = new Artista();

        artista.setNombre(dto.getNombre());
        artista.setApellidos(dto.getApellidos());
        artista.setFechaNacimiento(dto.getFechaNacimiento());
        artista.setFechaMuerte(dto.getFechaMuerte());
        artista.setLugarNacimiento(dto.getLugarNacimiento());
        artista.setBiografia(dto.getBiografia());
        artista.setEstilo(dto.getEstilo());
        artista.setImagen(dto.getImagen());
        return artista;
    }

    // ========== OPERACIÓN CREATE ==========
    @Transactional
    public ArtistaDTO saveArtista(ArtistaDTO artistaDTO) {
        Artista artista = mapToEntity(artistaDTO);
        Artista savedArtista = artistaRepository.save(artista);
        return mapToDTO(savedArtista);
    }

    // ========== OPERACIONES READ ==========
    @Transactional(readOnly = true)
    public ArtistaDTO findByNombreAndApellidos(String nombre, String apellidos) {
        Artista artista = artistaRepository.findByNombreAndApellidos(nombre, apellidos)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con nombre: " + nombre + " " + apellidos));
        return mapToDTO(artista);
    }

    @Transactional(readOnly = true)
    public List<ArtistaDTO> findByEstilo(String estilo) {
        return artistaRepository.findByEstilo(estilo)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ArtistaDTO> findByLugarNacimiento(String lugarNacimiento) {
        return artistaRepository.findByLugarNacimiento(lugarNacimiento)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ========== OPERACIÓN UPDATE ==========
    @Transactional
    public ArtistaDTO updateArtista(ArtistaDTO artistaDTO, Long idArtista) {
        Artista artistaDB = artistaRepository.findById(idArtista)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID: " + idArtista));
        // Actualizar solo campos no nulos
        if (Objects.nonNull(artistaDTO.getNombre()) && !artistaDTO.getNombre().isEmpty()) {
            artistaDB.setNombre(artistaDTO.getNombre());
        }
        if (Objects.nonNull(artistaDTO.getApellidos()) && !artistaDTO.getApellidos().isEmpty()) {
            artistaDB.setApellidos(artistaDTO.getApellidos());
        }
        if (Objects.nonNull(artistaDTO.getFechaNacimiento())) {
            artistaDB.setFechaNacimiento(artistaDTO.getFechaNacimiento());
        }
        if (Objects.nonNull(artistaDTO.getFechaMuerte())) {
            artistaDB.setFechaMuerte(artistaDTO.getFechaMuerte());
        }
        if (Objects.nonNull(artistaDTO.getLugarNacimiento()) && !artistaDTO.getLugarNacimiento().isEmpty()) {
            artistaDB.setLugarNacimiento(artistaDTO.getLugarNacimiento());
        }
        if (Objects.nonNull(artistaDTO.getBiografia()) && !artistaDTO.getBiografia().isEmpty()) {
            artistaDB.setBiografia(artistaDTO.getBiografia());
        }
        if (Objects.nonNull(artistaDTO.getEstilo()) && !artistaDTO.getEstilo().isEmpty()) {
            artistaDB.setEstilo(artistaDTO.getEstilo());
        }
        if (Objects.nonNull(artistaDTO.getImagen()) && !artistaDTO.getImagen().isEmpty()) {
            artistaDB.setImagen(artistaDTO.getImagen());
        }

        Artista updatedArtista = artistaRepository.save(artistaDB);
        return mapToDTO(updatedArtista);
    }

    // ========== OPERACIÓN DELETE ==========
    @Transactional
    public void deleteArtistaById(Long idArtista) {
        Artista artista = artistaRepository.findById(idArtista)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID: " + idArtista));

        // Verificar si el artista tiene obras
        if (obraRepository.existsByArtistaId(idArtista)) {
            // Eliminar obras del artista
            obraRepository.deleteByArtistaId(idArtista);
        }
        // Eliminar artista
        artistaRepository.deleteById(idArtista);
    }
}
