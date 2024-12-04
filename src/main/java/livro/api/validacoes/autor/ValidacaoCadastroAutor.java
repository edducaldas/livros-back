package livro.api.validacoes.autor;


import livro.api.dto.AutorDto;

public interface ValidacaoCadastroAutor {
    void validar(AutorDto dto, Long id);
}
