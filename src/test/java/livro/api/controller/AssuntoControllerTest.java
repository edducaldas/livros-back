package livro.api.controller;

import livro.api.dto.AssuntoDto;
import livro.api.service.AssuntoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssuntoControllerTest {

    @InjectMocks
    private AssuntoController assuntoController;

    @Mock
    private AssuntoService assuntoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() {
        AssuntoDto dto = new AssuntoDto(1L,"Romance");
        doNothing().when(assuntoService).cadastrar(dto);

        ResponseEntity<?> response = assuntoController.cadastrar(dto);

        assertEquals(200, response.getStatusCodeValue());
        verify(assuntoService, times(1)).cadastrar(dto);
    }

    @Test
    void testGetAllAutores() {
        List<AssuntoDto> assuntos = Arrays.asList(
                new AssuntoDto(1L,"Romance"),
                new AssuntoDto(2L,"Comédia")
        );
        when(assuntoService.getAllAutores()).thenReturn(assuntos);

        List<AssuntoDto> result = assuntoController.getAllAutores();

        assertEquals(2, result.size());
        assertEquals("Romance", result.get(0).descricao());
        verify(assuntoService, times(1)).getAllAutores();
    }

    @Test
    void testGetAutorById() {
        AssuntoDto dto = new AssuntoDto(1L,"Romance");
        when(assuntoService.getAssuntoById(1L)).thenReturn(dto);

        AssuntoDto result = assuntoController.getAutorById(1L);

        assertNotNull(result);
        assertEquals("Romance", result.descricao());
        verify(assuntoService, times(1)).getAssuntoById(1L);
    }

    @Test
    void testUpdate() {
        AssuntoDto dto = new AssuntoDto(1L,"Romance Alterado");
        when(assuntoService.alterar(1L, dto)).thenReturn(dto);

        AssuntoDto result = assuntoController.update(1L, dto);

        assertNotNull(result);
        assertEquals("Romance Alterado", result.descricao());
        verify(assuntoService, times(1)).alterar(1L, dto);
    }

    @Test
    void testDelete() {
        doNothing().when(assuntoService).delete(1L);

        ResponseEntity<Void> response = assuntoController.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(assuntoService, times(1)).delete(1L);
    }
}
