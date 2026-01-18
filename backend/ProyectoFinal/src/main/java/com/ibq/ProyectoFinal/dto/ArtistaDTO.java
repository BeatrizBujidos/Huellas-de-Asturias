package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ArtistaDTO {

    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private LocalDate fechaMuerte;
    private String lugarNacimiento;
    private String biografia;
    private String estilo;
    private String imagen;
}