package com.ibq.ProyectoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibq.ProyectoFinal.dto.MonumentoDTO;
import com.ibq.ProyectoFinal.service.MonumentoService;
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

@WebMvcTest(MonumentoController.class)
@ExtendWith(SpringExtension.class)
class MonumentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MonumentoService monumentoService;

    @Autowired
    private ObjectMapper objectMapper;

    private MonumentoDTO crearDTO() {
        return MonumentoDTO.builder().nombre("Coliseo").descripcion("Anfiteatro romano").fechaConstruccion("72 d.C.").latitud(41.8902).longitud(12.4922).epocaId(5L).epocaNombre("Imperio Romano").build();
    }

    // POST /monumentos
    @Test
    void saveMonumento_shouldReturnCreated() throws Exception {
        MonumentoDTO dto = crearDTO();

        Mockito.when(monumentoService.saveMonumento(any())).thenReturn(dto);

        mockMvc.perform(post("/api/monumentos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(jsonPath("$.nombre").value("Coliseo"));
    }

    // GET /monumentos/epoca/{id}
    @Test
    void findMonumentoByEpoca_shouldReturnList() throws Exception {
        MonumentoDTO dto = crearDTO();

        Mockito.when(monumentoService.findByEpocaId(5L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/monumentos/epoca/5")).andExpect(status().isOk()).andExpect(jsonPath("$[0].epocaNombre").value("Imperio Romano"));
    }

    // GET /monumentos/listado
    @Test
    void listarMonumentos_shouldReturnList() throws Exception {
        MonumentoDTO dto = crearDTO();

        Mockito.when(monumentoService.listarMonumentos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/monumentos/listado")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nombre").value("Coliseo"));
    }

    // PUT /monumentos/{id}
    @Test
    void updateMonumento_shouldReturnUpdated() throws Exception {
        MonumentoDTO dto = crearDTO();
        dto.setNombre("Nuevo Monumento");

        Mockito.when(monumentoService.updateMonumento(any(), eq(1L))).thenReturn(dto);

        mockMvc.perform(put("/api/monumentos/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$.nombre").value("Nuevo Monumento"));
    }

    // DELETE /monumentos/{id} - OK
    @Test
    void deleteMonumento_shouldReturnOk() throws Exception {
        Mockito.doNothing().when(monumentoService).deleteMonumentoById(1L);

        mockMvc.perform(delete("/api/monumentos/1")).andExpect(status().isOk()).andExpect(content().string("Monumento eliminado correctamente"));
    }

    // DELETE /monumentos/{id} - 404
    @Test
    void deleteMonumento_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Monumento no encontrado")).when(monumentoService).deleteMonumentoById(99L);

        mockMvc.perform(delete("/api/monumentos/99")).andExpect(status().isNotFound());
    }
}