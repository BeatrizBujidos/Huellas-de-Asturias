package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "monumentos")
public class Monumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_epoca", referencedColumnName = "id")
    private Epoca epoca;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String fecha_construccion;
    private String descripcion;
    private Double latitud;
    private Double longitud;

    @Transient
    private String imagenPrincipal;
    @Transient
    private List<String> imagenes = new ArrayList<>();
}
