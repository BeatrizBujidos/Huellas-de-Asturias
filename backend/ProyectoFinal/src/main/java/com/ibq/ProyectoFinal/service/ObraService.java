package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.model.Obra;
import com.ibq.ProyectoFinal.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ObraService {
    private final ObraRepository obraRepository;

    @Autowired
    public ObraService (ObraRepository obraRepository){
        this.obraRepository = obraRepository;
    }
    // ========== MAPPERS ==========
    // mapToDTO
    private ObraDTO mapToDTO(Obra obra) {
        ObraDTO.ObraDTOBuilder builder = ObraDTO.builder()
                .titulo(obra.getTitulo())
                .fechaCreacion(obra.getFechaCreacion())
                .tecnica(obra.getTecnica())
                .descripcion(obra.getDescripcion())
                .dimensiones(obra.getDimensiones());
        // Añadir información del artista si existe
        if (obra.getArtista() != null) {
            builder.artistaId(obra.getArtista().getId())
                    .artistaNombre(obra.getArtista().getNombre() + " " +
                            (obra.getArtista().getApellidos() != null ? obra.getArtista().getApellidos() : ""));
        }
        // Añadir información del museo si existe
        if (obra.getMuseo() != null) {
            builder.museoId(obra.getMuseo().getId())
                    .museoNombre(obra.getMuseo().getNombre());
        }
        // Añadir información de la época si existe
        if (obra.getEpoca() != null) {
            builder.epocaId(obra.getEpoca().getId())
                    .epocaNombre(obra.getEpoca().getNombre());
        }
        return builder.build();
    }
    //DTO → Entity
    private Obra mapToEntity(ObraDTO dto) {
        Obra obra = new Obra();

        obra.setTitulo(dto.getTitulo());
        obra.setFechaCreacion(dto.getFechaCreacion());
        obra.setTecnica(dto.getTecnica());
        obra.setDescripcion(dto.getDescripcion());
        obra.setDimensiones(dto.getDimensiones());
        return obra;
    }

    // ========== OPERACIÓN CREATE ==========
    @Transactional
    public ObraDTO saveObra(ObraDTO obraDTO) {
        Obra obra = mapToEntity(obraDTO);
        Obra savedObra = obraRepository.save(obra);
        return mapToDTO(savedObra);
    }

    // ========== OPERACIONES READ ==========
    @Transactional(readOnly = true)
    public ObraDTO findByTitulo(String titulo) {
        Obra obra = obraRepository.findByTitulo(titulo)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con título: " + titulo));
        return mapToDTO(obra);
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByArtistaId(Long idArtista) {
        return obraRepository.findByArtistaId(idArtista)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByMuseoId(Long idMuseo) {
        return obraRepository.findByMuseoId(idMuseo)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByEpocaId(Long idEpoca) {
        return obraRepository.findByEpocaId(idEpoca)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByTecnica(String tecnica) {
        return obraRepository.findByTecnica(tecnica)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ========== OPERACIÓN UPDATE ==========
    @Transactional
    public ObraDTO updateObra(ObraDTO obraDTO, Long idObra) {
        Obra obraDB = obraRepository.findById(idObra)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID: " + idObra));

        // Actualizar solo campos no nulos
        if (Objects.nonNull(obraDTO.getTitulo()) && !obraDTO.getTitulo().isEmpty()) {
            obraDB.setTitulo(obraDTO.getTitulo());
        }
        if (Objects.nonNull(obraDTO.getFechaCreacion())) {
            obraDB.setFechaCreacion(obraDTO.getFechaCreacion());
        }
        if (Objects.nonNull(obraDTO.getTecnica()) && !obraDTO.getTecnica().isEmpty()) {
            obraDB.setTecnica(obraDTO.getTecnica());
        }
        if (Objects.nonNull(obraDTO.getDescripcion()) && !obraDTO.getDescripcion().isEmpty()) {
            obraDB.setDescripcion(obraDTO.getDescripcion());
        }
        if (Objects.nonNull(obraDTO.getDimensiones()) && !obraDTO.getDimensiones().isEmpty()) {
            obraDB.setDimensiones(obraDTO.getDimensiones());
        }

        Obra updatedObra = obraRepository.save(obraDB);
        return mapToDTO(updatedObra);
    }

    // ========== OPERACIÓN DELETE ==========
    @Transactional
    public void deleteObraById(Long idObra) {
        if (!obraRepository.existsById(idObra)) {
            throw new RuntimeException("Obra no encontrada con ID: " + idObra);
        }
        obraRepository.deleteById(idObra);
    }
}
