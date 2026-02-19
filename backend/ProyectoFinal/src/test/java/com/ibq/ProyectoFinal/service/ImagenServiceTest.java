package com.ibq.ProyectoFinal.service;

import com.ibq.ProyectoFinal.dto.ImagenDTO;
import com.ibq.ProyectoFinal.model.Imagen;
import com.ibq.ProyectoFinal.repository.ImagenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImagenServiceTest {

    @Mock
    private ImagenRepository imagenRepository;

    @InjectMocks
    private ImagenService imagenService;

    private Imagen imagen;
    private ImagenDTO imagenDTO;

    @BeforeEach
    void setUp() {
        imagen = new Imagen();
        imagen.setId(1L);
        imagen.setUrl("https://cdn/obra/1.jpg");
        imagen.setTipoEntidad(Imagen.TipoEntidad.OBRA);
        imagen.setIdEntidad(10L);
        imagen.setEsPrincipal(true);
        imagen.setOrden(1);

        imagenDTO = ImagenDTO.builder().url("https://cdn/obra/1.jpg").tipoEntidad("obra").idEntidad(10L).esPrincipal(true).orden(1).build();
    }

    // CREATE
    @Test
    void saveImagen_shouldReturnDTO() {
        when(imagenRepository.save(any())).thenReturn(imagen);

        ImagenDTO result = imagenService.saveImagen(imagenDTO);

        assertEquals("https://cdn/obra/1.jpg", result.getUrl());
        verify(imagenRepository).save(any(Imagen.class));
    }

    // READ: findByUrl
    @Test
    void findByUrl_present_shouldReturnDTO() {
        when(imagenRepository.findByUrl("https://cdn/obra/1.jpg")).thenReturn(Optional.of(imagen));

        Optional<ImagenDTO> result = imagenService.findByUrl("https://cdn/obra/1.jpg");

        assertTrue(result.isPresent());
        assertEquals("https://cdn/obra/1.jpg", result.get().getUrl());
    }

    @Test
    void findByUrl_empty_shouldReturnEmpty() {
        when(imagenRepository.findByUrl("nope")).thenReturn(Optional.empty());

        Optional<ImagenDTO> result = imagenService.findByUrl("nope");

        assertTrue(result.isEmpty());
    }

    // READ: findByEntidad (usa ORDER BY orden ASC en repo)
    @Test
    void findByEntidad_shouldReturnOrderedList_andNormalizeEnum() {
        Imagen i1 = new Imagen();
        i1.setUrl("u1");
        i1.setTipoEntidad(Imagen.TipoEntidad.OBRA);
        i1.setIdEntidad(10L);
        i1.setOrden(1);
        Imagen i2 = new Imagen();
        i2.setUrl("u2");
        i2.setTipoEntidad(Imagen.TipoEntidad.OBRA);
        i2.setIdEntidad(10L);
        i2.setOrden(2);

        when(imagenRepository.findByTipoEntidadAndIdEntidadOrderByOrdenAsc(Imagen.TipoEntidad.OBRA, 10L)).thenReturn(List.of(i1, i2));

        List<ImagenDTO> result = imagenService.findByEntidad("obra", 10L);

        assertEquals(2, result.size());
        assertEquals("u1", result.get(0).getUrl());
        assertEquals("u2", result.get(1).getUrl());
    }

    // READ: principal
    @Test
    void findImagenPrincipal_present_shouldReturnDTO() {
        when(imagenRepository.findByTipoEntidadAndIdEntidadAndEsPrincipalTrue(Imagen.TipoEntidad.OBRA, 10L)).thenReturn(Optional.of(imagen));

        Optional<ImagenDTO> result = imagenService.findImagenPrincipal("obra", 10L);

        assertTrue(result.isPresent());
        assertTrue(result.get().getEsPrincipal());
    }

    @Test
    void findImagenPrincipal_empty_shouldReturnEmpty() {
        when(imagenRepository.findByTipoEntidadAndIdEntidadAndEsPrincipalTrue(Imagen.TipoEntidad.OBRA, 10L)).thenReturn(Optional.empty());

        Optional<ImagenDTO> result = imagenService.findImagenPrincipal("obra", 10L);

        assertTrue(result.isEmpty());
    }

    // READ: utilidades
    @Test
    void findUrlsByEntidad_shouldReturnOnlyUrls() {
        Imagen i = new Imagen();
        i.setUrl("u1");
        i.setTipoEntidad(Imagen.TipoEntidad.OBRA);
        i.setIdEntidad(10L);
        i.setOrden(1);
        when(imagenRepository.findByTipoEntidadAndIdEntidadOrderByOrdenAsc(Imagen.TipoEntidad.OBRA, 10L)).thenReturn(List.of(i));

        List<String> urls = imagenService.findUrlsByEntidad("obra", 10L);

        assertEquals(List.of("u1"), urls);
    }

    @Test
    void findUrlImagenPrincipal_present_shouldReturnOnlyUrl() {
        when(imagenRepository.findByTipoEntidadAndIdEntidadAndEsPrincipalTrue(Imagen.TipoEntidad.OBRA, 10L)).thenReturn(Optional.of(imagen));

        Optional<String> url = imagenService.findUrlImagenPrincipal("obra", 10L);

        assertTrue(url.isPresent());
        assertEquals("https://cdn/obra/1.jpg", url.get());
    }

    // UPDATE
    @Test
    void updateImagen_shouldPatchAndReturnDTO() {
        Imagen existing = new Imagen();
        existing.setId(2L);
        existing.setUrl("old");
        existing.setTipoEntidad(Imagen.TipoEntidad.OBRA);
        existing.setIdEntidad(10L);
        existing.setEsPrincipal(false);
        existing.setOrden(5);

        when(imagenRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(imagenRepository.save(any())).thenReturn(existing);

        ImagenDTO cambios = ImagenDTO.builder().url("new").orden(1).build();
        ImagenDTO result = imagenService.updateImagen(cambios, 2L);

        assertEquals("new", result.getUrl());
        verify(imagenRepository).save(any(Imagen.class));
    }

    @Test
    void updateImagen_notFound_shouldThrow() {
        when(imagenRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> imagenService.updateImagen(imagenDTO, 999L));
    }

    // setImagenPrincipal: desmarca anteriores y marca la nueva
    @Test
    void setImagenPrincipal_shouldUnsetAllAndSetOne() {
        Imagen other = new Imagen();
        other.setId(5L);
        other.setTipoEntidad(Imagen.TipoEntidad.OBRA);
        other.setIdEntidad(10L);
        other.setEsPrincipal(true);
        other.setOrden(2);

        // 'imagen' (la del setUp) será la que marcaremos como principal (id=1L)
        when(imagenRepository.findByTipoEntidadAndIdEntidad(Imagen.TipoEntidad.OBRA, 10L)).thenReturn(List.of(other, imagen));
        when(imagenRepository.findById(1L)).thenReturn(Optional.of(imagen));

        //Verificar el estado exacto cuando se invoca saveAll
        doAnswer(inv -> {
            Iterable<Imagen> it = inv.getArgument(0);
            boolean allUnset = StreamSupport.stream(it.spliterator(), false).allMatch(img -> Boolean.FALSE.equals(img.getEsPrincipal()));
            assertTrue(allUnset, "Todas las imágenes deberían estar desmarcadas como principales antes de marcar la nueva");
            return null;
        }).when(imagenRepository).saveAll(any());

        imagenService.setImagenPrincipal(1L, "obra", 10L);

        //Verificar que se marcó la imagen solicitada como principal
        verify(imagenRepository, atLeastOnce()).save(argThat(img -> img.getId().equals(1L) && Boolean.TRUE.equals(img.getEsPrincipal())));
    }

    // DELETE
    @Test
    void deleteImagenById_shouldDelete() {
        when(imagenRepository.existsById(1L)).thenReturn(true);

        imagenService.deleteImagenById(1L);

        verify(imagenRepository).deleteById(1L);
    }

    @Test
    void deleteImagenById_notFound_shouldThrow() {
        when(imagenRepository.existsById(999L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> imagenService.deleteImagenById(999L));
    }

    @Test
    void deleteAllImagenesByEntidad_shouldCallRepoWithEnum() {
        imagenService.deleteAllImagenesByEntidad("obra", 10L);

        verify(imagenRepository).deleteByTipoEntidadAndIdEntidad(Imagen.TipoEntidad.OBRA, 10L);
    }
}