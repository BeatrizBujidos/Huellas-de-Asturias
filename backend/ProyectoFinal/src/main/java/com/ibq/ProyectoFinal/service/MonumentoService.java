package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.MonumentoDTO;
import com.ibq.ProyectoFinal.model.Monumento;
import com.ibq.ProyectoFinal.repository.MonumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MonumentoService {
    private final MonumentoRepository monumentoRepository;
    @Autowired
    public MonumentoService (MonumentoRepository monumentoRepository){
        this.monumentoRepository = monumentoRepository;
    }
// ========== MAPPERS ==========
    // mapToDTO
    private MonumentoDTO mapToDTO(Monumento monumento) {
        MonumentoDTO.MonumentoDTOBuilder builder = MonumentoDTO.builder()
                .nombre(monumento.getNombre())
                .descripcion(monumento.getDescripcion())
                .fechaConstruccion(monumento.getFechaConstruccion())
                .latitud(monumento.getLatitud())
                .longitud(monumento.getLongitud());

        // Añadir información de la época si existe
        if (monumento.getEpoca() != null) {
            builder.epocaId(monumento.getEpoca().getId())
                    .epocaNombre(monumento.getEpoca().getNombre());
        }

        return builder.build();
    }

    //DTO → Entity
    private Monumento mapToEntity(MonumentoDTO dto) {
        Monumento monumento = new Monumento();

        monumento.setNombre(dto.getNombre());
        monumento.setDescripcion(dto.getDescripcion());
        monumento.setFechaConstruccion(dto.getFechaConstruccion());
        monumento.setLatitud(dto.getLatitud());
        monumento.setLongitud(dto.getLongitud());
        return monumento;
    }

    // ========== OPERACIÓN CREATE ==========
    @Transactional
    public MonumentoDTO saveMonumento(MonumentoDTO monumentoDTO) {
        Monumento monumento = mapToEntity(monumentoDTO);
        Monumento savedMonumento = monumentoRepository.save(monumento);
        return mapToDTO(savedMonumento);
    }

    // ========== OPERACIONES READ ==========
    @Transactional(readOnly = true)
    public List<MonumentoDTO> listarMonumentos() {
        return monumentoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonumentoDTO> findByEpocaId(Long idEpoca) {
        return monumentoRepository.findByEpocaId(idEpoca)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MonumentoDTO findByNombre(String nombre) {
        Monumento monumento = monumentoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Monumento no encontrado con nombre: " + nombre));
        return mapToDTO(monumento);
    }
    //Buscar monumentos en un área geográfica (para el mapa interactivo)
    @Transactional(readOnly = true)
    public List<MonumentoDTO> findByArea(Double latMin, Double latMax, Double lonMin, Double lonMax) {
        return monumentoRepository.findByLatitudBetweenAndLongitudBetween(latMin, latMax, lonMin, lonMax)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ========== OPERACIÓN UPDATE ==========
    @Transactional
    public MonumentoDTO updateMonumento(MonumentoDTO monumentoDTO, Long idMonumento) {
        Monumento monumentoDB = monumentoRepository.findById(idMonumento)
                .orElseThrow(() -> new RuntimeException("Monumento no encontrado con ID: " + idMonumento));

        // Actualizar solo campos no nulos
        if (Objects.nonNull(monumentoDTO.getNombre()) && !monumentoDTO.getNombre().isEmpty()) {
            monumentoDB.setNombre(monumentoDTO.getNombre());
        }
        if (Objects.nonNull(monumentoDTO.getFechaConstruccion()) && !monumentoDTO.getFechaConstruccion().isEmpty()) {
            monumentoDB.setFechaConstruccion(monumentoDTO.getFechaConstruccion());
        }
        if (Objects.nonNull(monumentoDTO.getDescripcion()) && !monumentoDTO.getDescripcion().isEmpty()) {
            monumentoDB.setDescripcion(monumentoDTO.getDescripcion());
        }
        if (Objects.nonNull(monumentoDTO.getLatitud())) {
            monumentoDB.setLatitud(monumentoDTO.getLatitud());
        }
        if (Objects.nonNull(monumentoDTO.getLongitud())) {
            monumentoDB.setLongitud(monumentoDTO.getLongitud());
        }

        Monumento updatedMonumento = monumentoRepository.save(monumentoDB);
        return mapToDTO(updatedMonumento);
    }

    // ========== OPERACIÓN DELETE ==========
    @Transactional
    public void deleteMonumentoById(Long idMonumento) {
        if (!monumentoRepository.existsById(idMonumento)) {
            throw new RuntimeException("Monumento no encontrado con ID: " + idMonumento);
        }
        monumentoRepository.deleteById(idMonumento);
    }
}
