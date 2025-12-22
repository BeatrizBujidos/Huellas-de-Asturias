package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Museo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseoRepository extends JpaRepository<Museo, Long> {

    List<Museo> findByCiudad(String ciudad);
    List<Museo> findAll();

}
