package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MonumentoDTO {
    private String nombre;
    private LocalDate fecha_construccion;
    private String descripcion;

}
