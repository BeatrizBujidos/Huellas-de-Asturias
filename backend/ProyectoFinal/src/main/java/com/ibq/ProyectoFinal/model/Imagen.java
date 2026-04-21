package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id;
    @NotBlank(message = "La URL es obligatoria")
    private String url;

    @NotNull(message = "El tipo de entidad es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entidad")
    private TipoEntidad tipoEntidad;

    @NotNull(message = "El ID de la entidad es obligatorio")
    @Column(name = "idEntidad")
    private Long idEntidad;

    @Column(name = "es_principal")
    private Boolean esPrincipal = false;

    private int orden = 1;

    public enum TipoEntidad {
        OBRA, MONUMENTO
    }
}
