package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.model.Epoca;
import com.ibq.ProyectoFinal.repository.EpocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpocaService {
    private final EpocaRepository epocaRepository;
    @Autowired
    public EpocaService (EpocaRepository epocaRepository){
        this.epocaRepository = epocaRepository;
    }
    //Operación CREATE
    @Transactional
    public Epoca saveEpoca(Epoca epoca){
        return epocaRepository.save(epoca);
    }
//Operaciones READ
//mapToRequestDTO
    private EpocaDTO mapToRequestDTO(Epoca epoca){
        return EpocaDTO.builder()
                .nombre(epoca.getNombre())
                .descripcion(epoca.getDescripcion())
                .fecha_inicio(epoca.getFecha_inicio())
                .fecha_fin(epoca.getFecha_fin())
                .caracteristicas(epoca.getCaracteristicas())
                .build();
    }
//Buscar época por nombre
    @Transactional(readOnly = true)
    public EpocaDTO findByNombre(String nombre){
        Epoca epoca = epocaRepository.findByNombre(nombre);
        return EpocaDTO.builder()
                .id(epoca.getId())
                .nombre(epoca.getNombre())
                .descripcion(epoca.getDescripcion())
                .fecha_inicio(epoca.getFecha_inicio())
                .fecha_fin(epoca.getFecha_fin())
                .caracteristicas(epoca.getCaracteristicas())
                .build();
    }
    //Listar épocas
    @Transactional(readOnly = true)
    public List<EpocaDTO> listarEpocas(){
        return epocaRepository.findAll()
                .stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
    //Operación UPDATE
    @Transactional
    public Epoca updateEpoca(Epoca epoca, Long idEpoca){
        Optional<Epoca> epocaOptional =epocaRepository.findById(idEpoca);
        if (!epocaOptional.isPresent()){
            return null;
        }
        Epoca epocaDB = epocaOptional.get();

        if (Objects.nonNull(epoca.getNombre()) && (!epoca.getNombre().isEmpty())){
            epocaDB.setNombre(epoca.getNombre());
        }
        if (Objects.nonNull(epoca.getDescripcion()) && (!epoca.getDescripcion().isEmpty())){
            epocaDB.setDescripcion(epoca.getDescripcion());
        }
        if (Objects.nonNull(epoca.getFecha_inicio())){
            epocaDB.setFecha_inicio(epoca.getFecha_inicio());
        }
        if (Objects.nonNull(epoca.getFecha_fin())){
            epocaDB.setFecha_fin(epoca.getFecha_fin());
        }
        if (Objects.nonNull(epoca.getCaracteristicas()) && (!epoca.getCaracteristicas().isEmpty())){
            epocaDB.setCaracteristicas(epoca.getCaracteristicas());
        }

        return epocaRepository.save(epocaDB);
    }
    //Operación DELETE
    @Transactional
    public void deleteEpocaById(Long idEpoca){
        epocaRepository.deleteById(idEpoca);
    }

}
