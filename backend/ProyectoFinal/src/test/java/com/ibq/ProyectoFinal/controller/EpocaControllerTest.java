package com.ibq.ProyectoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.service.EpocaService;
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

@WebMvcTest(EpocaController.class)
@ExtendWith(SpringExtension.class)
class EpocaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EpocaService epocaService;

    @Autowired
    private ObjectMapper objectMapper;

    private EpocaDTO crearDTO() {
        return EpocaDTO.builder().nombre("Neoclasicismo").descripcion("Descripción de la época").fechaInicio(1760).fechaFin(1840).caracteristicas("Arte innovador").build();
    }

    // POST /epocas
    @Test
    void saveEpoca_shouldReturnCreated() throws Exception {
        EpocaDTO dto = crearDTO();

        Mockito.when(epocaService.saveEpoca(any())).thenReturn(dto);

        mockMvc.perform(post("/api/epocas").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(jsonPath("$.nombre").value("Neoclasicismo"));
    }

    // GET /epocas/nombre
    @Test
    void findEpocaByNombre_shouldReturnOk() throws Exception {
        EpocaDTO dto = crearDTO();

        Mockito.when(epocaService.findByNombre("Neoclasicismo")).thenReturn(dto);

        mockMvc.perform(get("/api/epocas/nombre").param("nombre", "Neoclasicismo")).andExpect(status().isOk()).andExpect(jsonPath("$.fechaInicio").value(1760));
    }

    // GET /epocas/listado
    @Test
    void listarEpocas_shouldReturnList() throws Exception {
        EpocaDTO dto = crearDTO();

        Mockito.when(epocaService.listarEpocas()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/epocas/listado")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nombre").value("Neoclasicismo"));
    }

    // PUT /epocas/{id}
    @Test
    void updateEpoca_shouldReturnUpdated() throws Exception {
        EpocaDTO dto = crearDTO();
        dto.setNombre("Nueva Época");

        Mockito.when(epocaService.updateEpoca(any(), eq(1L))).thenReturn(dto);

        mockMvc.perform(put("/api/epocas/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$.nombre").value("Nueva Época"));
    }

    // DELETE /epocas/{id} - OK
    @Test
    void deleteEpoca_shouldReturnOk() throws Exception {
        Mockito.doNothing().when(epocaService).deleteEpocaById(1L);

        mockMvc.perform(delete("/api/epocas/1")).andExpect(status().isOk()).andExpect(content().string("Época eliminada correctamente"));
    }

    // DELETE /epocas/{id} - 404
    @Test
    void deleteEpoca_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Época no encontrada")).when(epocaService).deleteEpocaById(99L);

        mockMvc.perform(delete("/api/epocas/99")).andExpect(status().isNotFound());
    }
}