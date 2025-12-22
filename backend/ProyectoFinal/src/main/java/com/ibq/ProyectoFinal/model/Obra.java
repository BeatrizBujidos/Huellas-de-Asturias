package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_artista", referencedColumnName = "id")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "id_museo", referencedColumnName = "id")
    private Museo museo;

    @ManyToOne
    @JoinColumn(name = "id_epoca", referencedColumnName = "id")
    private Epoca epoca;

    private String titulo;
    private LocalDate fecha_creacion;
    private String tecnica;
    private String descripcion;
    private String dimensiones;
    private String imagen;
}
