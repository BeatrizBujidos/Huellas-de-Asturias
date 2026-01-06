package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "museos")
public class Museo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del museo es obligatorio")
    private String nombre;
    @NotBlank(message = "La dirección del museo es obligatorio")
    private String direccion;

    private String ciudad;
    private Double latitud;
    private Double longitud;
    private String horario;

    @NotBlank(message = "La dirección web es obligatoria")
    private String web;

    private String imagen;

    @OneToMany(mappedBy = "museo")
    private List<Obra> obras;
}
