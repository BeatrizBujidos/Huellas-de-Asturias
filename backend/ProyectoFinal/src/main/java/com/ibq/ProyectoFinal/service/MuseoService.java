package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.MuseoDTO;
import com.ibq.ProyectoFinal.model.Museo;
import com.ibq.ProyectoFinal.repository.MuseoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MuseoService {
    private final MuseoRepository museoRepository;
    @Autowired
    public MuseoService (MuseoRepository museoRepository){
        this.museoRepository = museoRepository;
    }
// ========== MAPPERS ==========

    // mapToDTO
    private MuseoDTO mapToDTO(Museo museo) {
        return MuseoDTO.builder()
                .nombre(museo.getNombre())
                .direccion(museo.getDireccion())
                .ciudad(museo.getCiudad())
                .latitud(museo.getLatitud())
                .longitud(museo.getLongitud())
                .horario(museo.getHorario())
                .web(museo.getWeb())
                .imagen(museo.getImagen())
                .build();
    }

    //DTO → Entity
    private Museo mapToEntity(MuseoDTO dto) {
        Museo museo = new Museo();

        museo.setNombre(dto.getNombre());
        museo.setDireccion(dto.getDireccion());
        museo.setCiudad(dto.getCiudad());
        museo.setLatitud(dto.getLatitud());
        museo.setLongitud(dto.getLongitud());
        museo.setHorario(dto.getHorario());
        museo.setWeb(dto.getWeb());
        museo.setImagen(dto.getImagen());
        return museo;
    }

    // ========== OPERACIÓN CREATE ==========
    @Transactional
    public MuseoDTO saveMuseo(MuseoDTO museoDTO) {
        Museo museo = mapToEntity(museoDTO);
        Museo savedMuseo = museoRepository.save(museo);
        return mapToDTO(savedMuseo);
    }

    // ========== OPERACIONES READ ==========
    @Transactional(readOnly = true)
    public List<MuseoDTO> listarMuseos() {
        return museoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MuseoDTO> findByCiudad(String ciudad) {
        return museoRepository.findByCiudad(ciudad)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MuseoDTO findByNombre(String nombre) {
        Museo museo = museoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Museo no encontrado con nombre: " + nombre));
        return mapToDTO(museo);
    }

    //Buscar museos en un área geográfica (para el mapa interactivo)
    @Transactional(readOnly = true)
    public List<MuseoDTO> findByArea(Double latMin, Double latMax, Double lonMin, Double lonMax) {
        return museoRepository.findByLatitudBetweenAndLongitudBetween(latMin, latMax, lonMin, lonMax)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ========== OPERACIÓN UPDATE ==========
    @Transactional
    public MuseoDTO updateMuseo(MuseoDTO museoDTO, Long idMuseo) {
        Museo museoDB = museoRepository.findById(idMuseo)
                .orElseThrow(() -> new RuntimeException("Museo no encontrado con ID: " + idMuseo));

        // Actualizar solo campos no nulos
        if (Objects.nonNull(museoDTO.getNombre()) && !museoDTO.getNombre().isEmpty()) {
            museoDB.setNombre(museoDTO.getNombre());
        }
        if (Objects.nonNull(museoDTO.getDireccion()) && !museoDTO.getDireccion().isEmpty()) {
            museoDB.setDireccion(museoDTO.getDireccion());
        }
        if (Objects.nonNull(museoDTO.getCiudad()) && !museoDTO.getCiudad().isEmpty()) {
            museoDB.setCiudad(museoDTO.getCiudad());
        }
        if (Objects.nonNull(museoDTO.getLatitud())) {
            museoDB.setLatitud(museoDTO.getLatitud());
        }
        if (Objects.nonNull(museoDTO.getLongitud())) {
            museoDB.setLongitud(museoDTO.getLongitud());
        }
        if (Objects.nonNull(museoDTO.getHorario()) && !museoDTO.getHorario().isEmpty()) {
            museoDB.setHorario(museoDTO.getHorario());
        }
        if (Objects.nonNull(museoDTO.getWeb()) && !museoDTO.getWeb().isEmpty()) {
            museoDB.setWeb(museoDTO.getWeb());
        }
        if (Objects.nonNull(museoDTO.getImagen()) && !museoDTO.getImagen().isEmpty()) {
            museoDB.setImagen(museoDTO.getImagen());
        }

        Museo updatedMuseo = museoRepository.save(museoDB);
        return mapToDTO(updatedMuseo);
    }

    // ========== OPERACIÓN DELETE ==========
    @Transactional
    public void deleteMuseoById(Long idMuseo) {
        if (!museoRepository.existsById(idMuseo)) {
            throw new RuntimeException("Museo no encontrado con ID: " + idMuseo);
        }
        museoRepository.deleteById(idMuseo);
    }
}
