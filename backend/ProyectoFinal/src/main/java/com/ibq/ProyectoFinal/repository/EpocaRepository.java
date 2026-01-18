package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Epoca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpocaRepository extends JpaRepository<Epoca, Long> {

    Optional<Epoca> findByNombre(String nombre);
}
