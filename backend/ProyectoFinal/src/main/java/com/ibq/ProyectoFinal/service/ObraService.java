package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.exception.ResourceNotFoundException;
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
    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    // mapToDTO
    private ObraDTO mapToDTO(Obra obra) {
        ObraDTO.ObraDTOBuilder builder = ObraDTO.builder()
                .id(obra.getId())
                .titulo(obra.getTitulo())
                .fechaCreacion(obra.getFechaCreacion())
                .tecnica(obra.getTecnica())
                .descripcion(obra.getDescripcion())
                .descripcionEn(obra.getDescripcionEn())
                .dimensiones(obra.getDimensiones());
        // Añadir informacion del artista si existe
        if (obra.getArtista() != null) {
            builder.artistaId(obra.getArtista()
                            .getId())
                    .artistaNombre(obra.getArtista()
                            .getNombre() + " " + (obra.getArtista()
                            .getApellidos() != null ? obra.getArtista().getApellidos() : ""));
        }
        // Añadir informacion del museo si existe
        if (obra.getMuseo() != null) {
            builder.museoId(obra.getMuseo().getId())
                    .museoNombre(obra.getMuseo().getNombre());
        }
        // Añadir informacion de la epoca si existe
        if (obra.getEpoca() != null) {
            builder.epocaId(obra.getEpoca().getId())
                    .epocaNombre(obra.getEpoca().getNombre());
        }
        return builder.build();
    }

    //DTO → Entity
    private Obra mapToEntity(ObraDTO dto) {
        Obra obra = new Obra();

        obra.setId(dto.getId());
        obra.setTitulo(dto.getTitulo());
        obra.setFechaCreacion(dto.getFechaCreacion());
        obra.setTecnica(dto.getTecnica());
        obra.setDescripcion(dto.getDescripcion());
        obra.setDescripcionEn(dto.getDescripcionEn());
        obra.setDimensiones(dto.getDimensiones());
        return obra;
    }

    // Operacion CREATE
    @Transactional
    public ObraDTO saveObra(ObraDTO obraDTO) {
        Obra obra = mapToEntity(obraDTO);
        Obra savedObra = obraRepository.save(obra);
        return mapToDTO(savedObra);
    }

    // Operaciones READ
    @Transactional(readOnly = true)
    public ObraDTO findById(Long id) {
        Obra obra = obraRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con el id: " + id));
        return mapToDTO(obra);
    }

    @Transactional(readOnly = true)
    public ObraDTO findByTitulo(String titulo) {
        Obra obra = obraRepository.findByTitulo(titulo).orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con el título: " + titulo));
        return mapToDTO(obra);
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByArtistaId(Long idArtista) {
        return obraRepository.findByArtistaId(idArtista).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByMuseoId(Long idMuseo) {
        return obraRepository.findByMuseoId(idMuseo).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByEpocaId(Long idEpoca) {
        return obraRepository.findByEpocaId(idEpoca).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByTecnica(String tecnica) {
        return obraRepository.findByTecnica(tecnica).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByArtistaNombre(String nombreArtista) {
        return obraRepository.findByArtista_Nombre(nombreArtista).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByMuseoNombre(String nombreMuseo) {
        return obraRepository.findByMuseo_Nombre(nombreMuseo).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> findByEpocaNombre(String nombreEpoca) {
        return obraRepository.findByEpoca_Nombre(nombreEpoca).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraDTO> listAll() {
        return obraRepository.findAll()
                .stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    // Operacion UPDATE
    @Transactional
    public ObraDTO updateObra(ObraDTO obraDTO, Long idObra) {
        Obra obraDB = obraRepository.findById(idObra).orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con el ID: " + idObra));

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
        if (Objects.nonNull(obraDTO.getDescripcionEn()) && !obraDTO.getDescripcionEn().isEmpty()) {
            obraDB.setDescripcionEn(obraDTO.getDescripcionEn());
        }
        if (Objects.nonNull(obraDTO.getDimensiones()) && !obraDTO.getDimensiones().isEmpty()) {
            obraDB.setDimensiones(obraDTO.getDimensiones());
        }

        Obra updatedObra = obraRepository.save(obraDB);
        return mapToDTO(updatedObra);
    }

    // Operacion DELETE
    @Transactional
    public void deleteObraById(Long idObra) {
        if (!obraRepository.existsById(idObra)) {
            throw new ResourceNotFoundException("Obra no encontrada con el ID: " + idObra);
        }
        obraRepository.deleteById(idObra);
    }
}
