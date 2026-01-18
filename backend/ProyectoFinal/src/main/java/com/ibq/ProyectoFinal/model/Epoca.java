package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "epocas")
public class Epoca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull
    @Min(value = 0, message = "La fecha de inicio debe ser un año válido")
    private Integer fechaInicio;
    @Min(value = 0, message = "La fecha de fin debe ser un año válido")
    private Integer fechaFin;

    private String caracteristicas;

    @OneToMany(mappedBy = "epoca")
    private List<Obra> obras;

    @OneToMany(mappedBy = "epoca")
    private List<Monumento> monumentos;

    @AssertTrue(message = "La fecha de fin debe ser posterior a la de inicio")
    public boolean isFechaFinValida() {
        if (fechaInicio == null || fechaFin == null) {
            return true;
        }
        return fechaFin > fechaInicio;
    }
}
