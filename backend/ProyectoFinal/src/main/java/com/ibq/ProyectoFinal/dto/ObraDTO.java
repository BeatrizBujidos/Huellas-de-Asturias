package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ObraDTO {

    private Long id;
    private String titulo;
    private Integer fechaCreacion;
    private String tecnica;
    private String descripcion;
    private String descripcionEn;
    private String dimensiones;

    private Long artistaId;
    private Long museoId;
    private Long epocaId;

    private String artistaNombre;
    private String museoNombre;
    private String epocaNombre;
}
