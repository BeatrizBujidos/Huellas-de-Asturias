package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.model.Epoca;
import com.ibq.ProyectoFinal.repository.EpocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EpocaService {
    private final EpocaRepository epocaRepository;

    @Autowired
    public EpocaService (EpocaRepository epocaRepository){
        this.epocaRepository = epocaRepository;
    }

    // ========== MAPPERS ==========
    // mapToDTO
    private EpocaDTO mapToDTO(Epoca epoca) {
        return EpocaDTO.builder()
                .nombre(epoca.getNombre())
                .descripcion(epoca.getDescripcion())
                .fechaInicio(epoca.getFechaInicio())
                .fechaFin(epoca.getFechaFin())
                .caracteristicas(epoca.getCaracteristicas())
                .build();
    }

    //DTO → Entity
    private Epoca mapToEntity(EpocaDTO dto) {
        Epoca epoca = new Epoca();

        epoca.setNombre(dto.getNombre());
        epoca.setDescripcion(dto.getDescripcion());
        epoca.setFechaInicio(dto.getFechaInicio());
        epoca.setFechaFin(dto.getFechaFin());
        epoca.setCaracteristicas(dto.getCaracteristicas());
        return epoca;
    }

    // ========== OPERACIÓN CREATE ==========
    @Transactional
    public EpocaDTO saveEpoca(EpocaDTO epocaDTO) {
        Epoca epoca = mapToEntity(epocaDTO);
        Epoca savedEpoca = epocaRepository.save(epoca);
        return mapToDTO(savedEpoca);
    }

    // ========== OPERACIONES READ ==========
    @Transactional(readOnly = true)
    public EpocaDTO findByNombre(String nombre) {
        Epoca epoca = epocaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Época no encontrada con nombre: " + nombre));
        return mapToDTO(epoca);
    }

    @Transactional(readOnly = true)
    public List<EpocaDTO> listarEpocas() {
        return epocaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ========== OPERACIÓN UPDATE ==========
    @Transactional
    public EpocaDTO updateEpoca(EpocaDTO epocaDTO, Long idEpoca) {
        Epoca epocaDB = epocaRepository.findById(idEpoca)
                .orElseThrow(() -> new RuntimeException("Época no encontrada con ID: " + idEpoca));

        // Actualizar solo campos no nulos
        if (Objects.nonNull(epocaDTO.getNombre()) && !epocaDTO.getNombre().isEmpty()) {
            epocaDB.setNombre(epocaDTO.getNombre());
        }
        if (Objects.nonNull(epocaDTO.getDescripcion()) && !epocaDTO.getDescripcion().isEmpty()) {
            epocaDB.setDescripcion(epocaDTO.getDescripcion());
        }
        if (Objects.nonNull(epocaDTO.getFechaInicio())) {
            epocaDB.setFechaInicio(epocaDTO.getFechaInicio());
        }
        if (Objects.nonNull(epocaDTO.getFechaFin())) {
            epocaDB.setFechaFin(epocaDTO.getFechaFin());
        }
        if (Objects.nonNull(epocaDTO.getCaracteristicas()) && !epocaDTO.getCaracteristicas().isEmpty()) {
            epocaDB.setCaracteristicas(epocaDTO.getCaracteristicas());
        }

        Epoca updatedEpoca = epocaRepository.save(epocaDB);
        return mapToDTO(updatedEpoca);
    }

    // ========== OPERACIÓN DELETE ==========
    @Transactional
    public void deleteEpocaById(Long idEpoca) {
        if (!epocaRepository.existsById(idEpoca)) {
            throw new RuntimeException("Época no encontrada con ID: " + idEpoca);
        }
        epocaRepository.deleteById(idEpoca);
    }
}