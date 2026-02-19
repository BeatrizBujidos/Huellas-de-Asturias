package com.ibq.ProyectoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.service.ArtistaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArtistaController.class)
class ArtistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArtistaService artistaService;

    @Autowired
    private ObjectMapper objectMapper;

    private ArtistaDTO crearDTO() {
        return ArtistaDTO.builder().nombre("Pablo").apellidos("Picasso").fechaNacimiento(LocalDate.of(1881, 10, 25)).estilo("Cubismo").build();
    }

    // POST /api/artistas
    @Test
    void saveArtista_shouldReturnCreated() throws Exception {
        ArtistaDTO dto = crearDTO();

        Mockito.when(artistaService.saveArtista(any())).thenReturn(dto);

        mockMvc.perform(post("/api/artistas").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(jsonPath("$.nombre").value("Pablo"))
                .andExpect(jsonPath("$.apellidos").value("Picasso"));
    }

    // GET /api/artistas/nombreYapellidos
    @Test
    void findArtistaByNombreAndApellidos_shouldReturnOk() throws Exception {
        ArtistaDTO dto = crearDTO();

        Mockito.when(artistaService.findByNombreAndApellidos("Pablo", "Picasso")).thenReturn(dto);

        mockMvc.perform(get("/api/artistas/nombreYapellidos").param("nombre", "Pablo").param("apellidos", "Picasso")).andExpect(status().isOk()).andExpect(jsonPath("$.nombre").value("Pablo"))
                .andExpect(jsonPath("$.estilo").value("Cubismo"));
    }

    // GET /api/artistas/estilo
    @Test
    void findArtistaByEstilo_shouldReturnList() throws Exception {
        ArtistaDTO dto = crearDTO();

        Mockito.when(artistaService.findByEstilo("Cubismo")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/artistas/estilo").param("estilo", "Cubismo")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nombre").value("Pablo"));
    }

    // PUT /api/artistas/{id}
    @Test
    void updateArtista_shouldReturnUpdated() throws Exception {
        ArtistaDTO dto = crearDTO();
        dto.setNombre("NuevoNombre");

        Mockito.when(artistaService.updateArtista(any(), eq(1L))).thenReturn(dto);

        mockMvc.perform(put("/api/artistas/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$.nombre").value("NuevoNombre"));
    }

    // DELETE /api/artistas/{id} - éxito
    @Test
    void deleteArtista_shouldReturn204() throws Exception {
        Mockito.doNothing().when(artistaService).deleteArtistaById(1L);

        mockMvc.perform(delete("/api/artistas/1")).andExpect(status().isNoContent());
    }

    // DELETE /api/artistas/{id} - error 404
    @Test
    void deleteArtista_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Artista no encontrado")).when(artistaService).deleteArtistaById(99L);

        mockMvc.perform(delete("/api/artistas/99")).andExpect(status().isNotFound());
    }
}