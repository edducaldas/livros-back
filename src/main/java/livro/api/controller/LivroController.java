package livro.api.controller;

import jakarta.validation.Valid;
import livro.api.dto.LivroDto;
import livro.api.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;




    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroDto dto) {
            //Usuario usuario = (Usuario) authentication.getPrincipal();
            this.livroService.cadastrar(dto);
            return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<LivroDto> getAllLivros() {
        return livroService.getAllLivros();
    }

    @GetMapping("/{id}")
    public LivroDto getLivroById(@PathVariable Long id) {
        return livroService.getLivroById(id);
    }

    @PutMapping("/{id}")
    public LivroDto updateLivro(@PathVariable Long id, @RequestBody LivroDto dto) {
        return livroService.alterar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{livroId}/autores")
//    public ResponseEntity<Livro> adicionarAutores(@PathVariable Long livroId, @RequestBody List<Long> autorIds) {
//        Livro livroAtualizado = livroService.adicionarAutores(livroId, autorIds);
//        return ResponseEntity.ok(livroAtualizado);
//    }

}
