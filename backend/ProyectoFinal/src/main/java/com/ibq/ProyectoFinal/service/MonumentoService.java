package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.dto.MonumentoDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.model.Monumento;
import com.ibq.ProyectoFinal.repository.MonumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonumentoService {
    private final MonumentoRepository monumentoRepository;
    @Autowired
    public MonumentoService (MonumentoRepository monumentoRepository){
        this.monumentoRepository = monumentoRepository;
    }
//Operación CREATE
    @Transactional
    public Monumento saveMonumento(Monumento monumento){
        return monumentoRepository.save(monumento);
    }
//Operaciones READ
//mapToRequestDTO
    private MonumentoDTO mapToRequestDTO(Monumento monumento){
        return MonumentoDTO.builder()
                .nombre(monumento.getNombre())
                .descripcion(monumento.getDescripcion())
                .fecha_construccion(monumento.getFecha_construccion())
                .build();
    }

//Listar monumentos
    @Transactional(readOnly = true)
    public List<MonumentoDTO> listarMonumentos(){
        return monumentoRepository.findAll()
                .stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
//Buscar monumento por epoca
    @Transactional(readOnly = true)
    public List<MonumentoDTO> findByEpocaId(Long idEpoca){
        return monumentoRepository.findByEpocaId(idEpoca).stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
//Operación UPDATE
    @Transactional
    public Monumento updateMonumento(Monumento monumento, Long idMonumento){
        Optional<Monumento> monumentoOptional = monumentoRepository.findById(idMonumento);
        if(!monumentoOptional.isPresent()){
            return null;
        }
        Monumento monumentoDB = monumentoOptional.get();

        if (Objects.nonNull(monumento.getNombre()) && !monumento.getNombre().isEmpty()){
            monumentoDB.setNombre(monumento.getNombre());
        }
        if (Objects.nonNull(monumento.getFecha_construccion())){
            monumentoDB.setFecha_construccion(monumento.getFecha_construccion());
        }
        if (Objects.nonNull(monumento.getDescripcion()) && (!monumento.getDescripcion().isEmpty())){
            monumentoDB.setDescripcion(monumento.getDescripcion());
        }
        if (Objects.nonNull(monumento.getLatitud())){
            monumentoDB.setLatitud(monumento.getLatitud());
        }
        if (Objects.nonNull(monumento.getLongitud())){
            monumentoDB.setLongitud(monumento.getLongitud());
        }
        if (Objects.nonNull(monumento.getImagen()) && !monumento.getImagen().isEmpty()){
            monumentoDB.setImagen(monumento.getImagen());
        }
        return monumentoRepository.save(monumentoDB);
    }
//Operación DELETE
    @Transactional
    public void deleteMonumentoById(Long idMonumento){
        monumentoRepository.deleteById(idMonumento);
    }

}
