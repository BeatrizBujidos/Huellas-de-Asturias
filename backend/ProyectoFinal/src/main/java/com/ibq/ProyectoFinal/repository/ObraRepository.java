package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {
    List<Obra> findByArtistaId(Long idArtista);
    List<Obra> findByMuseoId(Long idMuseo);
    List<Obra> findByEpocaId(Long idEpoca);
    List<Obra> findByTecnica(String tecnica);
    Obra findByTitulo(String titulo);

    boolean existsByArtistaId(Long idArtista);
    void deleteByArtistaId(Long idArtista);
}
