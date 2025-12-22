package com.ibq.ProyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Epoca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull
    @Past(message = "La fecha de inicio debe ser anterior a la fecha actual")
    private LocalDate fecha_inicio;
    @Past(message = "La fecha de fin debe ser anterior a la fecha actual")
    private LocalDate fecha_fin;

    private String caracteristicas;

    @OneToMany(mappedBy = "epoca")
    private List<Obra> obras;

    @OneToMany(mappedBy = "epoca")
    private List<Monumento> monumentos;

    @AssertTrue(message = "La fecha de fin debe ser posterior a la de inicio")
    public boolean isFechaFinValida() {
        if (fecha_fin == null || fecha_inicio == null) {
            return true;
        }
        return fecha_fin.isAfter(fecha_inicio);
    }
}
