package livro.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import livro.api.dto.AutorDto;
import livro.api.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class AutorController {


    private final AutorService autorService;
    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Operation(summary = "Cadastrar um autor", description = "Adiciona um novo autor ao sistema")
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorDto dto) {
            this.autorService.cadastrar(dto);
            return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de autores cadastrados")
    @GetMapping
    public List<AutorDto> getAllAutores() {
        return autorService.getAllAutores();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obter autor por ID",
            description = "Recupera os detalhes de um autor específico com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    public AutorDto getAutorById(@PathVariable Long id) {
        return autorService.getAutorById(id);
    }

    @PutMapping("/{id}")
    public AutorDto update(@PathVariable Long id, @RequestBody AutorDto dto) {
        return autorService.alterar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
