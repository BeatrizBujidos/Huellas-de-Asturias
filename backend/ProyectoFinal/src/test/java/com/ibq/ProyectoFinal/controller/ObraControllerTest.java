package com.ibq.ProyectoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.service.ObraService;
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

@WebMvcTest(ObraController.class)
@ExtendWith(SpringExtension.class)
class ObraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ObraService obraService;

    @Autowired
    private ObjectMapper objectMapper;

    private ObraDTO crearDTO() {
        return ObraDTO.builder().titulo("La Noche Estrellada").fechaCreacion(1889).tecnica("Óleo sobre lienzo").descripcion("Obra maestra de Van Gogh").dimensiones("73.7 cm × 92.1 cm").artistaId(1L).artistaNombre("Vincent van Gogh")
                .museoId(2L).museoNombre("MoMA").epocaId(3L).epocaNombre("Postimpresionismo").build();
    }

    // POST /obras
    @Test
    void saveObra_shouldReturnCreated() throws Exception {
        ObraDTO dto = crearDTO();
        Mockito.when(obraService.saveObra(any())).thenReturn(dto);

        mockMvc.perform(post("/api/obras").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(jsonPath("$.titulo").value("La Noche Estrellada"));
    }

    // GET /obras/titulo/{titulo}
    @Test
    void buscarObrasPorTitulo_shouldReturnObra() throws Exception {
        ObraDTO dto = crearDTO();
        Mockito.when(obraService.findByTitulo("La Noche Estrellada")).thenReturn(dto);

        mockMvc.perform(get("/api/obras/titulo/La Noche Estrellada")).andExpect(status().isOk()).andExpect(jsonPath("$.artistaNombre").value("Vincent van Gogh"));
    }

    // GET /obras/tecnica/{tecnica}
    @Test
    void buscarObrasPorTecnica_shouldReturnList() throws Exception {
        ObraDTO dto = crearDTO();
        Mockito.when(obraService.findByTecnica("Óleo sobre lienzo")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/obras/tecnica/Óleo sobre lienzo")).andExpect(status().isOk()).andExpect(jsonPath("$[0].tecnica").value("Óleo sobre lienzo"));
    }

    // GET /obras/artista/nombre/{nombre}
    @Test
    void buscarObrasPorArtistaNombre_shouldReturnList() throws Exception {
        ObraDTO dto = crearDTO();
        Mockito.when(obraService.findByArtistaNombre("Picasso")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/obras/artista/nombre/Picasso")).andExpect(status().isOk()).andExpect(jsonPath("$[0].artistaNombre").value("Vincent van Gogh"));
    }

    // GET /obras/museo/nombre/{nombre}
    @Test
    void buscarObrasPorMuseoNombre_shouldReturnList() throws Exception {
        ObraDTO dto = crearDTO();
        Mockito.when(obraService.findByMuseoNombre("Prado")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/obras/museo/nombre/Prado")).andExpect(status().isOk()).andExpect(jsonPath("$[0].museoNombre").value("MoMA"));
    }

    // GET /obras/epoca/nombre/{nombre}
    @Test
    void buscarObrasPorEpocaNombre_shouldReturnList() throws Exception {
        ObraDTO dto = crearDTO();
        Mockito.when(obraService.findByEpocaNombre("Renacimiento")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/obras/epoca/nombre/Renacimiento")).andExpect(status().isOk()).andExpect(jsonPath("$[0].epocaNombre").value("Postimpresionismo"));
    }

    // PUT /obras/{id}
    @Test
    void updateObra_shouldReturnUpdated() throws Exception {
        ObraDTO dto = crearDTO();
        dto.setTitulo("Nuevo Título");
        Mockito.when(obraService.updateObra(any(), eq(1L))).thenReturn(dto);

        mockMvc.perform(put("/api/obras/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Nuevo Título"));
    }

    // DELETE /obras/{id} - OK
    @Test
    void deleteObra_shouldReturnOk() throws Exception {
        Mockito.doNothing().when(obraService).deleteObraById(1L);

        mockMvc.perform(delete("/api/obras/1")).andExpect(status().isOk()).andExpect(content().string("Obra eliminada correctamente"));
    }

    // DELETE /obras/{id} - 404
    @Test
    void deleteObra_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Obra no encontrada")).when(obraService).deleteObraById(99L);

        mockMvc.perform(delete("/api/obras/99")).andExpect(status().isNotFound());
    }
}