package com.ibq.ProyectoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibq.ProyectoFinal.dto.MuseoDTO;
import com.ibq.ProyectoFinal.service.MuseoService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MuseoController.class)
@ExtendWith(SpringExtension.class)
class MuseoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MuseoService museoService;

    @Autowired
    private ObjectMapper objectMapper;

    private MuseoDTO crearDTO() {
        return MuseoDTO.builder().nombre("Museo del Prado").direccion("Calle Ruiz de Alarcón 23").ciudad("Madrid").latitud(40.4138).longitud(-3.6921).horario("09:00 - 20:00").web("https://www.museodelprado.es").imagen("prado.jpg").build();
    }

    // POST /museos
    @Test
    void saveMuseo_shouldReturnCreated() throws Exception {
        MuseoDTO dto = crearDTO();

        Mockito.when(museoService.saveMuseo(any())).thenReturn(dto);

        mockMvc.perform(post("/api/museos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(jsonPath("$.nombre").value("Museo del Prado"));
    }

    // GET /museos/{ciudad}
    @Test
    void findByCiudad_shouldReturnList() throws Exception {
        MuseoDTO dto = crearDTO();

        Mockito.when(museoService.findByCiudad("Madrid")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/museos/Madrid")).andExpect(status().isOk()).andExpect(jsonPath("$[0].ciudad").value("Madrid"));
    }

    // GET /museos/listado
    @Test
    void listarMuseos_shouldReturnList() throws Exception {
        MuseoDTO dto = crearDTO();

        Mockito.when(museoService.listarMuseos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/museos/listado")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nombre").value("Museo del Prado"));
    }

    // PUT /museos/{id}
    @Test
    void updateMuseo_shouldReturnUpdated() throws Exception {
        MuseoDTO dto = crearDTO();
        dto.setNombre("Museo Actualizado");

        Mockito.when(museoService.updateMuseo(any(), eq(1L))).thenReturn(dto);

        mockMvc.perform(put("/api/museos/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$.nombre").value("Museo Actualizado"));
    }

    // DELETE /museos/{id} - OK
    @Test
    void deleteMuseo_shouldReturnOk() throws Exception {
        Mockito.doNothing().when(museoService).deleteMuseoById(1L);

        mockMvc.perform(delete("/api/museos/1")).andExpect(status().isOk()).andExpect(content().string("Museo eliminado correctamente"));
    }

    // DELETE /museos/{id} - 404
    @Test
    void deleteMuseo_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Museo no encontrado")).when(museoService).deleteMuseoById(99L);

        mockMvc.perform(delete("/api/museos/99")).andExpect(status().isNotFound());
    }
}