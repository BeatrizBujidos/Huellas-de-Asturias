package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long>{

}
