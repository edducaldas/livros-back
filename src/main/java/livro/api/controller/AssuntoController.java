package livro.api.controller;

import jakarta.validation.Valid;
import livro.api.dto.AssuntoDto;
import livro.api.service.AssuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assuntos")
public class AssuntoController {

    @Autowired
    private AssuntoService assuntoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AssuntoDto dto) {
            this.assuntoService.cadastrar(dto);
            return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<AssuntoDto> getAllAutores() {
        return assuntoService.getAllAutores();
    }

    @GetMapping("/{id}")
    public AssuntoDto getAutorById(@PathVariable Long id) {
        return assuntoService.getAssuntoById(id);
    }

    @PutMapping("/{id}")
    public AssuntoDto update(@PathVariable Long id, @RequestBody AssuntoDto dto) {
        return assuntoService.alterar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assuntoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
