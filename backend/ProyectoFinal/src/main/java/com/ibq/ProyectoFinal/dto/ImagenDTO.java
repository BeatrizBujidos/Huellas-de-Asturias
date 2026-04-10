package com.ibq.ProyectoFinal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagenDTO {

    private Long id;
    private String url;
    private String tipoEntidad;
    private Long idEntidad;
    private Boolean esPrincipal;
    private Integer orden;
}
