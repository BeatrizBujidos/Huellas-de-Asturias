package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "obras")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    private int fecha_creacion;
    private String tecnica;
    private String descripcion;
    private String dimensiones;

    @Transient
    private String imagenPrincipal;
    @Transient
    private List<String> imagenes = new ArrayList<>();
}
