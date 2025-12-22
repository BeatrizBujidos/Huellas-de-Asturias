package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class EpocaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String caracteristicas;
}
