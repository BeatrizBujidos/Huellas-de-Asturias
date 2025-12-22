package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ObraDTO {
    private String titulo;
    private LocalDate fecha_creacion;
    private String descripcion;
    private String dimensiones;
    private String imagen;
}
