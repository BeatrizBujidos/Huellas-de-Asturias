package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MonumentoDTO {

    private String nombre;
    private String fechaConstruccion;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String imagen;

    private Long epocaId;
    private String epocaNombre;
}