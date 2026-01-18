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
    private Long id;
    @NotBlank(message = "La URL es obligatoria")
    private String url;

    @NotNull(message = "El tipo de entidad es obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoEntidad tipoEntidad;

    @NotNull(message = "El ID de la entidad es obligatorio")
    private Long idEntidad;

    private Boolean esPrincipal = false;

    private int orden = 1;

    public enum TipoEntidad{
        OBRA, MONUMENTO
    }
}
