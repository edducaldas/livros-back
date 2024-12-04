package livro.api.validacoes.livros;

import livro.api.dto.LivroDto;
import livro.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCamposNaoPreenchidoLivros implements ValidacaoCadastroLivro {

    public ValidacaoCamposNaoPreenchidoLivros() {
    }

    public void validar(LivroDto dto, Long id) {
        if (dto.editora() == null || dto.editora().isEmpty() ||
                dto.titulo() == null || dto.titulo().isEmpty() ||
                dto.anoPublicacao() == null || dto.anoPublicacao().isEmpty() ||
                dto.edicao() == null || dto.edicao() <= 0) {
            throw new ValidacaoException("Todos os campos tem que ser preencido");
        }

    }


}