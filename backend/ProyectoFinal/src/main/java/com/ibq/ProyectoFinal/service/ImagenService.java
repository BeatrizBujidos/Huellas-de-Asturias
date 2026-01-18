package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ImagenDTO;
import com.ibq.ProyectoFinal.model.Imagen;
import com.ibq.ProyectoFinal.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImagenService {
    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenService (ImagenRepository imagenRepository){
        this.imagenRepository = imagenRepository;
    }
    // ========== MAPPERS ==========

    // mapToDTO
    private ImagenDTO mapToDTO(Imagen imagen) {
        return ImagenDTO.builder()
                .url(imagen.getUrl())
                .tipoEntidad(imagen.getTipoEntidad().name())
                .idEntidad(imagen.getIdEntidad())
                .esPrincipal(imagen.getEsPrincipal())
                .orden(imagen.getOrden())
                .build();
    }

    //DTO → Entity
    private Imagen mapToEntity(ImagenDTO dto) {
        Imagen imagen = new Imagen();

        imagen.setUrl(dto.getUrl());
        imagen.setTipoEntidad(Imagen.TipoEntidad.valueOf(dto.getTipoEntidad().toUpperCase()));
        imagen.setIdEntidad(dto.getIdEntidad());
        imagen.setEsPrincipal(dto.getEsPrincipal() != null ? dto.getEsPrincipal() : false);
        imagen.setOrden(dto.getOrden() != null ? dto.getOrden() : 1);
        return imagen;
    }

    // ========== OPERACIÓN CREATE ==========
    @Transactional
    public ImagenDTO saveImagen(ImagenDTO imagenDTO) {
        Imagen imagen = mapToEntity(imagenDTO);
        Imagen savedImagen = imagenRepository.save(imagen);
        return mapToDTO(savedImagen);
    }

    // ========== OPERACIONES READ ==========
    @Transactional(readOnly = true)
    public Optional<ImagenDTO> findByUrl(String url) {
        return imagenRepository.findByUrl(url)
                .map(this::mapToDTO);
    }

    @Transactional(readOnly = true)
    public List<ImagenDTO> findByEntidad(String tipoEntidad, Long idEntidad) {
        Imagen.TipoEntidad tipo = Imagen.TipoEntidad.valueOf(tipoEntidad.toUpperCase());
        return imagenRepository.findByTipoEntidadAndIdEntidadOrderByOrdenAsc(tipo, idEntidad)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ImagenDTO> findImagenPrincipal(String tipoEntidad, Long idEntidad) {
        Imagen.TipoEntidad tipo = Imagen.TipoEntidad.valueOf(tipoEntidad.toUpperCase());
        return imagenRepository.findByTipoEntidadAndIdEntidadAndEsPrincipalTrue(tipo, idEntidad)
                .map(this::mapToDTO);
    }

    @Transactional(readOnly = true)
    public List<String> findUrlsByEntidad(String tipoEntidad, Long idEntidad) {
        return findByEntidad(tipoEntidad, idEntidad)
                .stream()
                .map(ImagenDTO::getUrl)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<String> findUrlImagenPrincipal(String tipoEntidad, Long idEntidad) {
        return findImagenPrincipal(tipoEntidad, idEntidad)
                .map(ImagenDTO::getUrl);
    }

    // ========== OPERACIÓN UPDATE ==========
    @Transactional
    public ImagenDTO updateImagen(ImagenDTO imagenDTO, Long idImagen) {
        Imagen imagenDB = imagenRepository.findById(idImagen)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con ID: " + idImagen));

        // Actualizar solo los campos que no sean null
        if (Objects.nonNull(imagenDTO.getUrl()) && !imagenDTO.getUrl().isEmpty()) {
            imagenDB.setUrl(imagenDTO.getUrl());
        }
        if (Objects.nonNull(imagenDTO.getTipoEntidad())) {
            imagenDB.setTipoEntidad(Imagen.TipoEntidad.valueOf(imagenDTO.getTipoEntidad().toUpperCase()));
        }
        if (Objects.nonNull(imagenDTO.getIdEntidad())) {
            imagenDB.setIdEntidad(imagenDTO.getIdEntidad());
        }
        if (Objects.nonNull(imagenDTO.getEsPrincipal())) {
            imagenDB.setEsPrincipal(imagenDTO.getEsPrincipal());
        }
        if (Objects.nonNull(imagenDTO.getOrden())) {
            imagenDB.setOrden(imagenDTO.getOrden());
        }

        Imagen updatedImagen = imagenRepository.save(imagenDB);
        return mapToDTO(updatedImagen);
    }

    @Transactional
    public void setImagenPrincipal(Long idImagen, String tipoEntidad, Long idEntidad) {
        Imagen.TipoEntidad tipo = Imagen.TipoEntidad.valueOf(tipoEntidad.toUpperCase());

        // Desmarcar todas las imágenes principales de esta entidad
        List<Imagen> imagenes = imagenRepository.findByTipoEntidadAndIdEntidad(tipo, idEntidad);
        imagenes.forEach(img -> img.setEsPrincipal(false));
        imagenRepository.saveAll(imagenes);

        // Marcar la nueva imagen principal
        imagenRepository.findById(idImagen).ifPresent(imagen -> {
            imagen.setEsPrincipal(true);
            imagenRepository.save(imagen);
        });
    }

    // ========== OPERACIONES DELETE ==========
    @Transactional
    public void deleteImagenById(Long id) {
        if (!imagenRepository.existsById(id)) {
            throw new RuntimeException("Imagen no encontrada con ID: " + id);
        }
        imagenRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllImagenesByEntidad(String tipoEntidad, Long idEntidad) {
        Imagen.TipoEntidad tipo = Imagen.TipoEntidad.valueOf(tipoEntidad.toUpperCase());
        imagenRepository.deleteByTipoEntidadAndIdEntidad(tipo, idEntidad);
    }
}