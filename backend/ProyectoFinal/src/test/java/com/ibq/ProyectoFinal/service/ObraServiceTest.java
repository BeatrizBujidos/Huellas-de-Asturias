package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ObraDTO;
import com.ibq.ProyectoFinal.model.Obra;
import com.ibq.ProyectoFinal.repository.ObraRepository;
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
class ObraServiceTest {

    @Mock
    private ObraRepository obraRepository;

    @InjectMocks
    private ObraService obraService;

    private Obra obra;
    private ObraDTO obraDTO;

    @BeforeEach
    void setUp() {
        obra = new Obra();
        obra.setId(1L);
        obra.setTitulo("La Noche Estrellada");
        obra.setFechaCreacion(1889);
        obra.setTecnica("Óleo sobre lienzo");
        obra.setDescripcion("Obra maestra de Van Gogh");
        obra.setDimensiones("73.7 cm × 92.1 cm");

        obraDTO = ObraDTO.builder().titulo("La Noche Estrellada").fechaCreacion(1889).tecnica("Óleo sobre lienzo").descripcion("Obra maestra de Van Gogh").dimensiones("73.7 cm × 92.1 cm").build();
    }

    //Create
    @Test
    void saveObra_shouldReturnDTO() {
        Mockito.when(obraRepository.save(Mockito.any())).thenReturn(obra);

        ObraDTO result = obraService.saveObra(obraDTO);

        assertEquals("La Noche Estrellada", result.getTitulo());
        Mockito.verify(obraRepository).save(Mockito.any(Obra.class));
    }

    //findByTitulo
    @Test
    void findByTitulo_shouldReturnDTO() {
        Mockito.when(obraRepository.findByTitulo("La Noche Estrellada")).thenReturn(Optional.of(obra));

        ObraDTO result = obraService.findByTitulo("La Noche Estrellada");

        assertEquals("La Noche Estrellada", result.getTitulo());
    }

    @Test
    void findByTitulo_notFound_shouldThrow() {
        Mockito.when(obraRepository.findByTitulo("La Noche Estrellada")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> obraService.findByTitulo("La Noche Estrellada"));
    }

    //findBy ids
    @Test
    void findByArtistaId_shouldReturnList() {
        Mockito.when(obraRepository.findByArtistaId(5L)).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByArtistaId(5L).size());
    }

    @Test
    void findByMuseoId_shouldReturnList() {
        Mockito.when(obraRepository.findByMuseoId(3L)).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByMuseoId(3L).size());
    }

    @Test
    void findByEpocaId_shouldReturnList() {
        Mockito.when(obraRepository.findByEpocaId(10L)).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByEpocaId(10L).size());
    }

    @Test
    void findByTecnica_shouldReturnList() {
        Mockito.when(obraRepository.findByTecnica("Óleo sobre lienzo")).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByTecnica("Óleo sobre lienzo").size());
    }

    //findBy nombres
    @Test
    void findByArtistaNombre_shouldReturnList() {
        Mockito.when(obraRepository.findByArtista_Nombre("Picasso")).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByArtistaNombre("Picasso").size());
    }

    @Test
    void findByMuseoNombre_shouldReturnList() {
        Mockito.when(obraRepository.findByMuseo_Nombre("Prado")).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByMuseoNombre("Prado").size());
    }

    @Test
    void findByEpocaNombre_shouldReturnList() {
        Mockito.when(obraRepository.findByEpoca_Nombre("Renacimiento")).thenReturn(List.of(obra));
        assertEquals(1, obraService.findByEpocaNombre("Renacimiento").size());
    }

    // UPDATE
    @Test
    void updateObra_shouldUpdateEntity() {
        Mockito.when(obraRepository.findById(1L)).thenReturn(Optional.of(obra));
        Mockito.when(obraRepository.save(Mockito.any())).thenReturn(obra);

        ObraDTO cambios = ObraDTO.builder().titulo("Nuevo Título").build();

        ObraDTO result = obraService.updateObra(cambios, 1L);
        assertEquals("Nuevo Título", result.getTitulo());
    }

    @Test
    void updateObra_notFound_shouldThrow() {
        Mockito.when(obraRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> obraService.updateObra(obraDTO, 1L));
    }

    // DELETE
    @Test
    void deleteObraById_shouldDelete() {
        Mockito.when(obraRepository.existsById(1L)).thenReturn(true);
        obraService.deleteObraById(1L);
        Mockito.verify(obraRepository).deleteById(1L);
    }

    @Test
    void deleteObraById_notFound_shouldThrow() {
        Mockito.when(obraRepository.existsById(1L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> obraService.deleteObraById(1L));
    }
}