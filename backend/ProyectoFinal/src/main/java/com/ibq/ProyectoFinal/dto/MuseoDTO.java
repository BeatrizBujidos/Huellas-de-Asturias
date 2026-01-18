package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MuseoDTO {

    private String nombre;
    private String direccion;
    private String ciudad;
    private Double latitud;
    private Double longitud;
    private String horario;
    private String web;
    private String imagen;
}
