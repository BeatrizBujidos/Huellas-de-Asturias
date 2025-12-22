package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.model.Obra;
import com.ibq.ProyectoFinal.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObraService {
    private final ObraRepository obraRepository;
    @Autowired
    public ObraService (ObraRepository obraRepository){
        this.obraRepository = obraRepository;
    }
//Operación CREATE
    public Obra saveObra(Obra obra){
        return obraRepository.save(obra);
    }
//Operaciones READ
//mapToRequestDTO
    private ObraDTO mapToRequestDTO(Obra obra){
        return ObraDTO.builder()
                .titulo(obra.getTitulo())
                .fecha_creacion(obra.getFecha_creacion())
                .descripcion(obra.getDescripcion())
                .dimensiones(obra.getDimensiones())
                .imagen(obra.getImagen())
                .build();
    }
    //Listar obras por artista
    @Transactional(readOnly = true)
    public List<ObraDTO> findByArtistaId(Long idArtista){
        return obraRepository.findByArtistaId(idArtista).stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
}
    //Listar obras por museo
    @Transactional(readOnly = true)
    public List<ObraDTO> findByMuseoId(Long idMuseo){
        return obraRepository.findByMuseoId(idMuseo).stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
    //Listar obras por época
    @Transactional(readOnly = true)
    public List<ObraDTO> findByEpocaId(Long idEpoca){
        return obraRepository.findByEpocaId(idEpoca).stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
    //Listar obras por tecnica
    @Transactional(readOnly = true)
    public List<ObraDTO> findByTecnica(String tecnica){
        return obraRepository.findByTecnica(tecnica).stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
    //Buscar obras por título
    @Transactional(readOnly = true)
    public Obra findByTitulo(String titulo){
        return obraRepository.findByTitulo(titulo);
    }
//Operación UPDATE
    @Transactional
    public Obra updateObra(Obra obra, Long idObra){
        Optional<Obra> obraOptional = obraRepository.findById(idObra);
        if(!obraOptional.isPresent()){
            return null;
        }
        Obra obraDB = obraOptional.get();

        if (Objects.nonNull(obra.getTitulo()) && !obra.getTitulo().isEmpty()){
            obraDB.setTitulo(obra.getTitulo());
        }
        if (Objects.nonNull(obra.getFecha_creacion())){
            obraDB.setFecha_creacion(obra.getFecha_creacion());
        }
        if (Objects.nonNull(obra.getTecnica()) && !obra.getTecnica().isEmpty()){
            obraDB.setTecnica(obra.getTecnica());
        }
        if (Objects.nonNull(obra.getDescripcion()) && !obra.getDescripcion().isEmpty()){
            obraDB.setDescripcion(obra.getDescripcion());
        }
        if (Objects.nonNull(obra.getDimensiones()) && !obra.getDimensiones().isEmpty()){
            obraDB.setDimensiones(obra.getDimensiones());
        }
        if (Objects.nonNull(obra.getImagen()) && !obra.getImagen().isEmpty()){
            obraDB.setImagen(obra.getImagen());
        }
        return obraRepository.save(obraDB);
    }
//Operación DELETE
    @Transactional
    public void deleteObraById(Long idObra){
        obraRepository.deleteById(idObra);
    }

}
