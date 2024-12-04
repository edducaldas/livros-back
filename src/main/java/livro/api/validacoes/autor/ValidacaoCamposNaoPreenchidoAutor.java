package livro.api.validacoes.autor;

import livro.api.dto.AutorDto;
import livro.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCamposNaoPreenchidoAutor implements ValidacaoCadastroAutor {
    public ValidacaoCamposNaoPreenchidoAutor() {
    }

    public void validar(AutorDto dto, Long id) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidacaoException("Todos os campos tem que ser preencido");
        }

    }
}
