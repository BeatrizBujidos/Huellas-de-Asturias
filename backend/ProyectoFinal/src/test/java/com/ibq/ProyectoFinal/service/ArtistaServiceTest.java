package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ArtistaDTO;
import com.ibq.ProyectoFinal.model.Artista;
import com.ibq.ProyectoFinal.repository.ArtistaRepository;
import com.ibq.ProyectoFinal.repository.ObraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ArtistaServiceTest {

    @Mock
    private ArtistaRepository artistaRepository;

    @Mock
    private ObraRepository obraRepository;

    @InjectMocks
    private ArtistaService artistaService;

    private Artista artista;
    private ArtistaDTO artistaDTO;

    @BeforeEach
    void setUp() {
        artista = new Artista();
        artista.setId(1L);
        artista.setNombre("Pablo");
        artista.setApellidos("Picasso");
        artista.setFechaNacimiento(LocalDate.of(1881, 10, 25));
        artista.setEstilo("Cubismo");
        artista.setLugarNacimiento("Málaga");

        artistaDTO = ArtistaDTO.builder().nombre("Pablo").apellidos("Picasso").fechaNacimiento(LocalDate.of(1881, 10, 25)).estilo("Cubismo").build();
    }

    //Save
    @Test
    void saveArtista_shouldSaveAndReturnDTO() {
        Mockito.when(artistaRepository.save(Mockito.any())).thenReturn(artista);

        ArtistaDTO result = artistaService.saveArtista(artistaDTO);

        assertEquals("Pablo", result.getNombre());
        assertEquals("Picasso", result.getApellidos());
        Mockito.verify(artistaRepository).save(Mockito.any(Artista.class));
    }

    //FindByNombreAndApellidos
    @Test
    void findByNombreAndApellidos_shouldReturnDTO() {
        Mockito.when(artistaRepository.findByNombreAndApellidos("Pablo", "Picasso")).thenReturn(Optional.of(artista));

        ArtistaDTO result = artistaService.findByNombreAndApellidos("Pablo", "Picasso");

        assertEquals("Pablo", result.getNombre());
        assertEquals("Picasso", result.getApellidos());
    }

    @Test
    void findByNombreAndApellidos_notFound_shouldThrowException() {
        Mockito.when(artistaRepository.findByNombreAndApellidos("Pablo", "Picasso")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> artistaService.findByNombreAndApellidos("Pablo", "Picasso"));
    }

    //FindByEstilo
    @Test
    void findByEstilo_shouldReturnList() {
        Mockito.when(artistaRepository.findByEstilo("Cubismo")).thenReturn(Arrays.asList(artista));

        List<ArtistaDTO> result = artistaService.findByEstilo("Cubismo");

        assertEquals(1, result.size());
        assertEquals("Cubismo", result.get(0).getEstilo());
    }

    //FindByLugarNacimiento

    @Test
    void findByLugarNacimiento_shouldReturnList() {
        Mockito.when(artistaRepository.findByLugarNacimiento("Málaga")).thenReturn(Arrays.asList(artista));

        List<ArtistaDTO> result = artistaService.findByLugarNacimiento("Málaga");

        assertEquals(1, result.size());
        assertEquals("Pablo", result.get(0).getNombre());
        assertEquals("Picasso", result.get(0).getApellidos());
    }

    @Test
    void findByLugarNacimiento_shouldReturnEmptyList() {
        Mockito.when(artistaRepository.findByLugarNacimiento("Madrid")).thenReturn(List.of());

        List<ArtistaDTO> result = artistaService.findByLugarNacimiento("Madrid");

        assertTrue(result.isEmpty());
    }

    //Update
    @Test
    void updateArtista_shouldModifyExistingEntity() {
        Mockito.when(artistaRepository.findById(1L)).thenReturn(Optional.of(artista));
        Mockito.when(artistaRepository.save(Mockito.any())).thenReturn(artista);

        ArtistaDTO cambios = ArtistaDTO.builder().nombre("NuevoNombre").build();

        ArtistaDTO result = artistaService.updateArtista(cambios, 1L);

        assertEquals("NuevoNombre", result.getNombre());
        Mockito.verify(artistaRepository).save(Mockito.any(Artista.class));
    }

    @Test
    void updateArtista_notFound_shouldThrow() {
        Mockito.when(artistaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> artistaService.updateArtista(artistaDTO, 1L));
    }

    //Delete
    @Test
    void deleteArtistaById_shouldDeleteArtistaAndObras() {
        Mockito.when(artistaRepository.findById(1L)).thenReturn(Optional.of(artista));
        Mockito.when(obraRepository.existsByArtistaId(1L)).thenReturn(true);

        artistaService.deleteArtistaById(1L);

        Mockito.verify(obraRepository).deleteByArtistaId(1L);
        Mockito.verify(artistaRepository).deleteById(1L);
    }

    @Test
    void deleteArtistaById_notFound_shouldThrow() {
        Mockito.when(artistaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> artistaService.deleteArtistaById(1L));
    }
}
