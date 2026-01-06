package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MonumentoDTO {
    private String nombre;
    private String fecha_construccion;
    private String descripcion;

}
