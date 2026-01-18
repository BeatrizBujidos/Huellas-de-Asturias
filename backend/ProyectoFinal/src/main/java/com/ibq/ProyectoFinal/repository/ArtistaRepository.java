package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNombreAndApellidos(String nombre, String apellidos);
    List<Artista> findByEstilo(String estilo);
    List<Artista> findByLugarNacimiento(String lugarNacimiento);
}
