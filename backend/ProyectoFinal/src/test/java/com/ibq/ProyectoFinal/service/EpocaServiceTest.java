package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.EpocaDTO;
import com.ibq.ProyectoFinal.model.Epoca;
import com.ibq.ProyectoFinal.repository.EpocaRepository;
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
class EpocaServiceTest {

    @Mock
    private EpocaRepository epocaRepository;

    @InjectMocks
    private EpocaService epocaService;

    private Epoca epoca;
    private EpocaDTO epocaDTO;

    @BeforeEach
    void setUp() {
        epoca = new Epoca();
        epoca.setId(1L);
        epoca.setNombre("Neoclasicismo");
        epoca.setDescripcion("Vuelta a los modelos clásicos de la antigüedad");
        epoca.setFechaInicio(1760);
        epoca.setFechaFin(1840);
        epoca.setCaracteristicas("Arte clásico");

        epocaDTO = EpocaDTO.builder().nombre("Neoclasicismo").descripcion("Etapa de vuelta al clasicismo").fechaInicio(1760).fechaFin(1840).caracteristicas("Arte clásico").build();
    }

    //Save
    @Test
    void saveEpoca_shouldSaveAndReturnDTO() {
        Mockito.when(epocaRepository.save(Mockito.any())).thenReturn(epoca);

        EpocaDTO result = epocaService.saveEpoca(epocaDTO);

        assertEquals("Neoclasicismo", result.getNombre());
        assertEquals(1760, result.getFechaInicio());
        Mockito.verify(epocaRepository).save(Mockito.any(Epoca.class));
    }

    //findByNombre
    @Test
    void findByNombre_shouldReturnDTO() {
        Mockito.when(epocaRepository.findByNombre("Neoclasicismo")).thenReturn(Optional.of(epoca));

        EpocaDTO result = epocaService.findByNombre("Neoclasicismo");

        assertEquals("Neoclasicismo", result.getNombre());
        assertEquals(1840, result.getFechaFin());
    }

    @Test
    void findByNombre_notFound_shouldThrow() {
        Mockito.when(epocaRepository.findByNombre("Neoclasicismo")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> epocaService.findByNombre("Neoclasicismo"));
    }

    //Lista de épocas
    @Test
    void listarEpocas_shouldReturnList() {
        Mockito.when(epocaRepository.findAll()).thenReturn(List.of(epoca));

        List<EpocaDTO> result = epocaService.listarEpocas();

        assertEquals(1, result.size());
        assertEquals("Neoclasicismo", result.get(0).getNombre());
    }

    //Update
    @Test
    void updateEpoca_shouldModifyEntity() {
        Mockito.when(epocaRepository.findById(1L)).thenReturn(Optional.of(epoca));
        Mockito.when(epocaRepository.save(Mockito.any())).thenReturn(epoca);

        EpocaDTO cambios = EpocaDTO.builder().nombre("NuevoNombre").build();

        EpocaDTO result = epocaService.updateEpoca(cambios, 1L);

        assertEquals("NuevoNombre", result.getNombre());
    }

    @Test
    void updateEpoca_notFound_shouldThrow() {
        Mockito.when(epocaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> epocaService.updateEpoca(epocaDTO, 1L));
    }

    //Delete
    @Test
    void deleteEpocaById_shouldDelete() {
        Mockito.when(epocaRepository.existsById(1L)).thenReturn(true);

        epocaService.deleteEpocaById(1L);

        Mockito.verify(epocaRepository).deleteById(1L);
    }

    @Test
    void deleteEpocaById_notFound_shouldThrow() {
        Mockito.when(epocaRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> epocaService.deleteEpocaById(99L));
    }
}