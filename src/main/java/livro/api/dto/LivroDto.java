package livro.api.dto;

import livro.api.model.Assunto;
import livro.api.model.Autor;
import livro.api.model.Livro;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record LivroDto(Long id, String titulo, String editora, Long edicao, String anoPublicacao, List<Long> autores, List<Long> assuntos, BigDecimal valor


) {
    public LivroDto(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getAutores().stream()
                        .map(Autor::getId)
                        .collect(Collectors.toList()),
                livro.getAssuntos().stream()
                        .map(Assunto::getId)
                        .collect(Collectors.toList()),
                livro.getValor()
        );
    }
}


