package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import lombok.Data;

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

    private String nombre;
    private String fecha_construccion;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String imagen;
}
