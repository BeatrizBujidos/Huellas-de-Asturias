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
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotNull
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;
    @Past(message = "La fecha de muerte debe ser anterior a la fecha actual")
    private LocalDate fechaMuerte;

    private String lugarNacimiento;
    private String biografia;
    private String estilo;
    private String imagen;

    @OneToMany(mappedBy = "artista")
    private List<Obra> obras;

    @AssertTrue(message = "La fecha de muerte debe ser posterior a la de nacimiento")
    public boolean isFechaMuerteValida() {
        if (fechaMuerte == null || fechaNacimiento == null) {
            return true;
        }
        return fechaMuerte.isAfter(fechaNacimiento);
    }
}
