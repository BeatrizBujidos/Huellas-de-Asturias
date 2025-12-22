package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Monumento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_epoca", referencedColumnName = "id")
    private Epoca epoca;

    private String nombre;
    private LocalDate fecha_construccion;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String imagen;
}
