package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Museo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MuseoRepository extends JpaRepository<Museo, Long> {

    List<Museo> findByCiudad(String ciudad);
    Optional<Museo> findByNombre(String nombre);
    List<Museo> findByLatitudBetweenAndLongitudBetween(Double latitudMin, Double latitudMax, Double longitudMin, Double longitudMax);
}