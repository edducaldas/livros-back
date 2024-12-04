package livro.api.dto;

import livro.api.model.Assunto;
import livro.api.model.Autor;

public record AssuntoDto(Long id, String descricao) {
    public AssuntoDto(Assunto assunto) {
        this(assunto.getId(), assunto.getDescricao());
    }
}
