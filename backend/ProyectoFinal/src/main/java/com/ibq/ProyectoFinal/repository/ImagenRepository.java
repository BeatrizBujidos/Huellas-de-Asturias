package com.ibq.ProyectoFinal.repository;

import com.ibq.ProyectoFinal.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long>{

    Optional<Imagen> findByUrl(String url);
    List<Imagen> findByTipoEntidadAndIdEntidad(Imagen.TipoEntidad tipoEntidad, Long idEntidad);
    Optional<Imagen> findByTipoEntidadAndIdEntidadAndEsPrincipalTrue(Imagen.TipoEntidad tipoEntidad, Long idEntidad);
    List<Imagen> findByTipoEntidadAndIdEntidadOrderByOrdenAsc(Imagen.TipoEntidad tipoEntidad, Long idEntidad);
    void deleteByTipoEntidadAndIdEntidad(Imagen.TipoEntidad tipoEntidad, Long idEntidad);
}