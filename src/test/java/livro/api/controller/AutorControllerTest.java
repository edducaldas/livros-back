package livro.api.controller;

import livro.api.dto.AutorDto;
import livro.api.service.AutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AutorControllerTest {

    private MockMvc mockMvc;
    private AutorService autorService;
    private AutorController autorController;

    @BeforeEach
    void setUp() {

        autorService = Mockito.mock(AutorService.class);
        autorController = new AutorController(autorService); // Injeta o mock no controller
        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();
    }

    @Test
    void testCadastrarAutor() throws Exception {
        //Quando o endpoint /api/autor recebe uma requisição POST válida, ele delega corretamente a lógica de cadastro para o serviço (autorService.cadastrar).
        //O controlador retorna um status HTTP 200 OK em caso de sucesso.
        //O método cadastrar do AutorService é efetivamente invocado uma vez.

        AutorDto autorDto = new AutorDto(null, "Autor Teste");

        // Simula o comportamento do serviço
        doNothing().when(autorService).cadastrar(any(AutorDto.class));

        mockMvc.perform(post("/api/autor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Autor Teste\"}"))
                .andExpect(status().isOk());

        verify(autorService, times(1)).cadastrar(any(AutorDto.class));

    }


}
