package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.dto.MuseoDTO;
import com.ibq.ProyectoFinal.model.Museo;
import com.ibq.ProyectoFinal.repository.MuseoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
//Operación CREATE
    @Transactional
    public Museo saveMuseo(Museo museo){
        return museoRepository.save(museo);
    }
//Operaciones READ
//mapToRequestDTO
    private MuseoDTO mapToRequestDTO(Museo museo){
        return MuseoDTO.builder()
                .nombre(museo.getNombre())
                .direccion(museo.getDireccion())
                .ciudad(museo.getCiudad())
                .horario(museo.getHorario())
                .web(museo.getWeb())
                .build();
    }
//Listar museos
    @Transactional(readOnly = true)
    public List<MuseoDTO> listarMuseos(){
        return museoRepository.findAll()
                .stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
//Listar museos por ciudad
    @Transactional(readOnly = true)
    public List<MuseoDTO> findByCiudad(String ciudad){
        List<Museo> museos = new ArrayList<>();
        museos = museoRepository.findByCiudad(ciudad);
        return museos.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }
//Operación UPDATE
    @Transactional
    public Museo updateMuseo(Museo museo,Long idMuseo){
        Museo museoDB = museoRepository.findById(idMuseo)
                .orElseThrow(()->new RuntimeException("Museo no encontrado"));

        if (Objects.nonNull(museo.getNombre())){
            museoDB.setNombre(museo.getNombre());
        }
        if (Objects.nonNull(museo.getDireccion())){
            museoDB.setDireccion(museo.getDireccion());
        }
        if (Objects.nonNull(museo.getCiudad())){
            museoDB.setCiudad(museo.getCiudad());
        }
        if (Objects.nonNull(museo.getLatitud())){
            museoDB.setLatitud(museo.getLatitud());
        }
        if (Objects.nonNull(museo.getLongitud())){
            museoDB.setLongitud(museo.getLongitud());
        }
        if (Objects.nonNull(museo.getHorario())){
            museoDB.setHorario(museo.getHorario());
        }
        if (Objects.nonNull(museo.getWeb())){
            museoDB.setWeb(museo.getWeb());
        }
        if (Objects.nonNull(museo.getImagen())){
            museoDB.setImagen(museo.getImagen());
        }
        return museoRepository.save(museoDB);
    }
//Operación DELETE
    @Transactional
    public void deleteMuseoById(Long idMuseo){
        museoRepository.deleteById(idMuseo);
    }
}
