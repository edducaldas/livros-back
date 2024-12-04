package livro.api.validacoes.livros;

import livro.api.dto.LivroDto;

public interface ValidacaoCadastroLivro {
    void validar(LivroDto dto, Long id);
}
