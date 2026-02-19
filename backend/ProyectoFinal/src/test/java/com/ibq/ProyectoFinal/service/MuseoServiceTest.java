package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.MuseoDTO;
import com.ibq.ProyectoFinal.model.Museo;
import com.ibq.ProyectoFinal.repository.MuseoRepository;
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
class MuseoServiceTest {

    @Mock
    private MuseoRepository museoRepository;

    @InjectMocks
    private MuseoService museoService;

    private Museo museo;
    private MuseoDTO museoDTO;

    @BeforeEach
    void setUp() {
        museo = new Museo();
        museo.setId(1L);
        museo.setNombre("Museo del Prado");
        museo.setDireccion("Calle Ruiz de Alarcón 23");
        museo.setCiudad("Madrid");
        museo.setLatitud(40.4138);
        museo.setLongitud(-3.6921);
        museo.setHorario("09:00 - 20:00");
        museo.setWeb("https://www.museodelprado.es");
        museo.setImagen("prado.jpg");

        museoDTO = MuseoDTO.builder().nombre("Museo del Prado").direccion("Calle Ruiz de Alarcón 23").ciudad("Madrid").latitud(40.4138).longitud(-3.6921).horario("09:00 - 20:00").web("https://www.museodelprado.es").imagen("prado.jpg")
                .build();
    }

    //Create
    @Test
    void saveMuseo_shouldReturnDTO() {
        Mockito.when(museoRepository.save(Mockito.any())).thenReturn(museo);

        MuseoDTO result = museoService.saveMuseo(museoDTO);

        assertEquals("Museo del Prado", result.getNombre());
        Mockito.verify(museoRepository).save(Mockito.any(Museo.class));
    }

    //Lista de museos
    @Test
    void listarMuseos_shouldReturnList() {
        Mockito.when(museoRepository.findAll()).thenReturn(List.of(museo));

        List<MuseoDTO> result = museoService.listarMuseos();

        assertEquals(1, result.size());
        assertEquals("Madrid", result.get(0).getCiudad());
    }

    //findByCiudad
    @Test
    void findByCiudad_shouldReturnList() {
        Mockito.when(museoRepository.findByCiudad("Madrid")).thenReturn(List.of(museo));

        List<MuseoDTO> result = museoService.findByCiudad("Madrid");

        assertEquals(1, result.size());
        assertEquals("Madrid", result.get(0).getCiudad());
    }

    //findByNombre
    @Test
    void findByNombre_shouldReturnDTO() {
        Mockito.when(museoRepository.findByNombre("Museo del Prado")).thenReturn(Optional.of(museo));

        MuseoDTO result = museoService.findByNombre("Museo del Prado");

        assertEquals("Museo del Prado", result.getNombre());
    }

    @Test
    void findByNombre_notFound_shouldThrow() {
        Mockito.when(museoRepository.findByNombre("Museo del Prado")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> museoService.findByNombre("Museo del Prado"));
    }

    //findByArea
    @Test
    void findByArea_shouldReturnList() {
        Mockito.when(museoRepository.findByLatitudBetweenAndLongitudBetween(40.0, 41.0, -4.0, -3.0)).thenReturn(List.of(museo));

        List<MuseoDTO> result = museoService.findByArea(40.0, 41.0, -4.0, -3.0);

        assertEquals(1, result.size());
        assertEquals("Museo del Prado", result.get(0).getNombre());
    }

    //Update
    @Test
    void updateMuseo_shouldUpdateEntity() {
        Mockito.when(museoRepository.findById(1L)).thenReturn(Optional.of(museo));
        Mockito.when(museoRepository.save(Mockito.any())).thenReturn(museo);

        MuseoDTO cambios = MuseoDTO.builder().nombre("Nuevo Museo").build();

        MuseoDTO result = museoService.updateMuseo(cambios, 1L);

        assertEquals("Nuevo Museo", result.getNombre());
    }

    @Test
    void updateMuseo_notFound_shouldThrow() {
        Mockito.when(museoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> museoService.updateMuseo(museoDTO, 1L));
    }

    //Delete
    @Test
    void deleteMuseoById_shouldDelete() {
        Mockito.when(museoRepository.existsById(1L)).thenReturn(true);

        museoService.deleteMuseoById(1L);

        Mockito.verify(museoRepository).deleteById(1L);
    }

    @Test
    void deleteMuseoById_notFound_shouldThrow() {
        Mockito.when(museoRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> museoService.deleteMuseoById(99L));
    }
}