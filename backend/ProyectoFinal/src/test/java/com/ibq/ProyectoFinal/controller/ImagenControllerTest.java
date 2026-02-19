package com.ibq.ProyectoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibq.ProyectoFinal.dto.ImagenDTO;
import com.ibq.ProyectoFinal.service.ImagenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImagenController.class)
@ExtendWith(SpringExtension.class)
class ImagenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ImagenService imagenService;

    @Autowired
    private ObjectMapper objectMapper;

    private ImagenDTO crearDTO() {
        return ImagenDTO.builder().url("https://cdn/obra/1.jpg").tipoEntidad("obra").idEntidad(10L).esPrincipal(true).orden(1).build();
    }

    // POST /imagenes
    @Test
    void saveImagen_shouldReturnCreated() throws Exception {
        ImagenDTO dto = crearDTO();
        Mockito.when(imagenService.saveImagen(any())).thenReturn(dto);

        mockMvc.perform(post("/api/imagenes").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(jsonPath("$.url").value("https://cdn/obra/1.jpg"));
    }

    // GET /imagenes/url?url=...
    @Test
    void getImagenByUrl_shouldReturnOk() throws Exception {
        ImagenDTO dto = crearDTO();
        Mockito.when(imagenService.findByUrl("https://cdn/obra/1.jpg")).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/imagenes/url").param("url", "https://cdn/obra/1.jpg")).andExpect(status().isOk()).andExpect(jsonPath("$.tipoEntidad").value("obra"));
    }

    @Test
    void getImagenByUrl_notFound_shouldReturn404() throws Exception {
        Mockito.when(imagenService.findByUrl("nope")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/imagenes/url").param("url", "nope")).andExpect(status().isNotFound());
    }

    // GET /imagenes/entidad/{tipoEntidad}/{idEntidad}
    @Test
    void getImagenesByEntidad_shouldReturnList() throws Exception {
        ImagenDTO dto = crearDTO();
        Mockito.when(imagenService.findByEntidad("obra", 10L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/imagenes/entidad/obra/10")).andExpect(status().isOk()).andExpect(jsonPath("$[0].url").value("https://cdn/obra/1.jpg"));
    }

    // GET /imagenes/principal/{tipoEntidad}/{idEntidad}
    @Test
    void getImagenPrincipal_shouldReturnOk() throws Exception {
        ImagenDTO dto = crearDTO();
        Mockito.when(imagenService.findImagenPrincipal("obra", 10L)).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/imagenes/principal/obra/10")).andExpect(status().isOk()).andExpect(jsonPath("$.esPrincipal").value(true));
    }

    @Test
    void getImagenPrincipal_notFound_shouldReturn404() throws Exception {
        Mockito.when(imagenService.findImagenPrincipal("obra", 10L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/imagenes/principal/obra/10")).andExpect(status().isNotFound());
    }

    // PUT /imagenes/{id}
    @Test
    void updateImagen_shouldReturnOk() throws Exception {
        ImagenDTO dto = crearDTO();
        dto.setOrden(2);

        Mockito.when(imagenService.updateImagen(any(), eq(1L))).thenReturn(dto);

        mockMvc.perform(put("/api/imagenes/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$.orden").value(2));
    }

    @Test
    void updateImagen_nullFromService_shouldReturn404() throws Exception {
        ImagenDTO dto = crearDTO();

        Mockito.when(imagenService.updateImagen(any(), eq(99L))).thenReturn(null);

        mockMvc.perform(put("/api/imagenes/99").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isNotFound());
    }

    // DELETE /imagenes/{id}
    @Test
    void deleteImagen_shouldReturn204() throws Exception {
        Mockito.doNothing().when(imagenService).deleteImagenById(1L);

        mockMvc.perform(delete("/api/imagenes/1")).andExpect(status().isNoContent());
    }

    @Test
    void deleteImagen_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("No encontrada")).when(imagenService).deleteImagenById(99L);

        mockMvc.perform(delete("/api/imagenes/99")).andExpect(status().isNotFound());
    }

    // DELETE /imagenes/entidad/{tipoEntidad}/{idEntidad}
    @Test
    void deleteAllImagenesByEntidad_shouldReturnOk() throws Exception {
        Mockito.doNothing().when(imagenService).deleteAllImagenesByEntidad("obra", 10L);

        mockMvc.perform(delete("/api/imagenes/entidad/obra/10")).andExpect(status().isOk()).andExpect(content().string("Todas las imágenes de la entidad eliminadas"));
    }

    @Test
    void deleteAllImagenesByEntidad_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Entidad no encontrada")).when(imagenService).deleteAllImagenesByEntidad("obra", 999L);

        mockMvc.perform(delete("/api/imagenes/entidad/obra/999")).andExpect(status().isNotFound());
    }
}