package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Enumerated(EnumType.STRING)
    private TipoEntidad tipoEntidad;

    public enum TipoEntidad{
        OBRA, MONUMENTO
    }

    private Long id_entidad;
    private Boolean es_principal;
    private int orden;
}
