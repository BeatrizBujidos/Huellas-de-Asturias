package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Monumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonumentoRepository extends JpaRepository<Monumento, Long> {
    List<Monumento> findByEpocaId(Long idEpoca);
    List<Monumento> findAll();
}
