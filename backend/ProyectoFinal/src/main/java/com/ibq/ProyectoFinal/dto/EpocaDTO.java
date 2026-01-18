package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class EpocaDTO {

    private String nombre;
    private String descripcion;
    private Integer fechaInicio;
    private Integer fechaFin;
    private String caracteristicas;
}
