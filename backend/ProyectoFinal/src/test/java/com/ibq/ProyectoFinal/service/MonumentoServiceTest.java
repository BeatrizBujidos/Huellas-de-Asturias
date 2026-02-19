package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.MonumentoDTO;
import com.ibq.ProyectoFinal.model.Monumento;
import com.ibq.ProyectoFinal.repository.MonumentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MonumentoServiceTest {

    @Mock
    private MonumentoRepository monumentoRepository;

    @InjectMocks
    private MonumentoService monumentoService;

    private Monumento monumento;
    private MonumentoDTO monumentoDTO;

    @BeforeEach
    void setUp() {
        monumento = new Monumento();
        monumento.setId(1L);
        monumento.setNombre("Coliseo");
        monumento.setDescripcion("Anfiteatro romano");
        monumento.setFechaConstruccion("72 d.C.");
        monumento.setLatitud(41.8902);
        monumento.setLongitud(12.4922);

        monumentoDTO = MonumentoDTO.builder().nombre("Coliseo").descripcion("Anfiteatro romano").fechaConstruccion("72 d.C.").latitud(41.8902).longitud(12.4922).build();
    }

    //Create
    @Test
    void saveMonumento_shouldReturnDTO() {
        Mockito.when(monumentoRepository.save(Mockito.any())).thenReturn(monumento);

        MonumentoDTO result = monumentoService.saveMonumento(monumentoDTO);

        assertEquals("Coliseo", result.getNombre());
        Mockito.verify(monumentoRepository).save(Mockito.any(Monumento.class));
    }

    //Listar monumentos
    @Test
    void listarMonumentos_shouldReturnList() {
        Mockito.when(monumentoRepository.findAll()).thenReturn(List.of(monumento));

        List<MonumentoDTO> result = monumentoService.listarMonumentos();

        assertEquals(1, result.size());
        assertEquals("Coliseo", result.get(0).getNombre());
    }

    //findByEpoca
    @Test
    void findByEpocaId_shouldReturnList() {
        Mockito.when(monumentoRepository.findByEpocaId(10L)).thenReturn(List.of(monumento));

        List<MonumentoDTO> result = monumentoService.findByEpocaId(10L);

        assertEquals(1, result.size());
        assertEquals("Coliseo", result.get(0).getNombre());
    }

    //findByNombre
    @Test
    void findByNombre_shouldReturnDTO() {
        Mockito.when(monumentoRepository.findByNombre("Coliseo")).thenReturn(Optional.of(monumento));

        MonumentoDTO result = monumentoService.findByNombre("Coliseo");

        assertEquals("Coliseo", result.getNombre());
    }

    @Test
    void findByNombre_notFound_shouldThrow() {
        Mockito.when(monumentoRepository.findByNombre("Coliseo")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> monumentoService.findByNombre("Coliseo"));
    }

    //findByArea
    @Test
    void findByArea_shouldReturnList() {
        Mockito.when(monumentoRepository.findByLatitudBetweenAndLongitudBetween(41.0, 42.0, 12.0, 13.0)).thenReturn(List.of(monumento));

        List<MonumentoDTO> result = monumentoService.findByArea(41.0, 42.0, 12.0, 13.0);

        assertEquals(1, result.size());
    }

    //Update
    @Test
    void updateMonumento_shouldModifyEntity() {
        Mockito.when(monumentoRepository.findById(1L)).thenReturn(Optional.of(monumento));
        Mockito.when(monumentoRepository.save(Mockito.any())).thenReturn(monumento);

        MonumentoDTO cambios = MonumentoDTO.builder().nombre("Nuevo nombre").build();

        MonumentoDTO result = monumentoService.updateMonumento(cambios, 1L);

        assertEquals("Nuevo nombre", result.getNombre());
    }

    @Test
    void updateMonumento_notFound_shouldThrow() {
        Mockito.when(monumentoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> monumentoService.updateMonumento(monumentoDTO, 1L));
    }

    //Delete
    @Test
    void deleteMonumentoById_shouldDelete() {
        Mockito.when(monumentoRepository.existsById(1L)).thenReturn(true);

        monumentoService.deleteMonumentoById(1L);

        Mockito.verify(monumentoRepository).deleteById(1L);
    }

    @Test
    void deleteMonumentoById_notFound_shouldThrow() {
        Mockito.when(monumentoRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> monumentoService.deleteMonumentoById(1L));
    }
}