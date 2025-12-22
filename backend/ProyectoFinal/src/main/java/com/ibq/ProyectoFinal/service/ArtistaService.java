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
import java.util.Optional;
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
//Operación CREATE
    @Transactional
    public Artista saveArtista(Artista artista){
        return artistaRepository.save(artista);
    }
//Operaciones READ
//mapToRequestDTO
    private ArtistaDTO mapToRequestDTO(Artista artista){
        return ArtistaDTO.builder()
                .nombre(artista.getNombre())
                .apellidos(artista.getApellidos())
                .fecha_nacimiento(artista.getFecha_nacimiento())
                .fecha_muerte(artista.getFecha_muerte())
                .lugar_nacimiento(artista.getLugar_nacimiento())
                .build();
    }
//Buscar artista por nombre y apellidos
    @Transactional(readOnly = true)
    public ArtistaDTO findByNombreAndApellidos(String nombre, String apellidos){
        Artista artista = artistaRepository.findByNombreAndApellidos(nombre, apellidos);
        return ArtistaDTO.builder()
                .nombre(artista.getNombre())
                .apellidos(artista.getApellidos())
                .fecha_nacimiento(artista.getFecha_nacimiento())
                .fecha_muerte(artista.getFecha_muerte())
                .lugar_nacimiento(artista.getLugar_nacimiento())
                .build();
    }
//Listar artistas por estilo
    @Transactional(readOnly = true)
    public List<ArtistaDTO> findByEstilo(String estilo){
        return artistaRepository.findByEstilo(estilo).stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
//Operación UPDATE
    @Transactional
    public Artista updateArtista(Artista artista, Long idArtista){
        Optional<Artista> artistaOptional = artistaRepository.findById(idArtista);
        if(!artistaOptional.isPresent()){
            return null;
        }
        Artista artistaDB = artistaOptional.get();

        if (Objects.nonNull(artista.getNombre()) && !artista.getNombre().isEmpty()){
            artistaDB.setNombre(artista.getNombre());
        }
        if (Objects.nonNull(artista.getApellidos()) && !artista.getApellidos().isEmpty()){
            artistaDB.setApellidos(artista.getApellidos());
        }
        if (Objects.nonNull(artista.getFecha_nacimiento())){
            artistaDB.setFecha_nacimiento(artista.getFecha_nacimiento());
        }
        if (Objects.nonNull(artista.getFecha_muerte())){
            artistaDB.setFecha_muerte(artista.getFecha_muerte());
        }
        if (Objects.nonNull(artista.getLugar_nacimiento()) && !artista.getLugar_nacimiento().isEmpty()){
            artistaDB.setLugar_nacimiento(artista.getLugar_nacimiento());
        }
        if (Objects.nonNull(artista.getBiografia()) && !artista.getBiografia().isEmpty()){
            artistaDB.setBiografia(artista.getBiografia());
        }
        if (Objects.nonNull(artista.getEstilo()) && !artista.getEstilo().isEmpty()){
            artistaDB.setEstilo(artista.getEstilo());
        }
        if (Objects.nonNull(artista.getImagen()) && !artista.getImagen().isEmpty()){
            artistaDB.setImagen(artista.getImagen());
        }
        return artistaRepository.save(artistaDB);
    }
//Operación DELETE
    @Transactional
    public void deleteArtistaById(Long idArtista){
        Optional<Artista> artistaOptional = artistaRepository.findById(idArtista);
        if (!artistaOptional.isPresent()){
            throw new RuntimeException("El artista no existe");
        }
        //Verificar si el artista tiene obras
        if(obraRepository.existsByArtistaId(idArtista)){
            //Eliminar obras del artista
            obraRepository.deleteByArtistaId(idArtista);
        }
        //Eliminar artista
        artistaRepository.deleteById(idArtista);
    }
}
