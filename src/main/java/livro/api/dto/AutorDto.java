package livro.api.dto;

import livro.api.model.Autor;

public record AutorDto(Long id, String nome) {
    public AutorDto(Autor autor) {
        this(autor.getId(), autor.getNome());
    }
}
