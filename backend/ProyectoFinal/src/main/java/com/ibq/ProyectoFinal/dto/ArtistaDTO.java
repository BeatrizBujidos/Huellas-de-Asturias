package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ArtistaDTO {
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_muerte;
    private String lugar_nacimiento;
}
