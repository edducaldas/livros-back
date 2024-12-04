package livro.api.validacoes.assunto;

import livro.api.dto.AssuntoDto;
import livro.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCamposNaoPreenchidoAssunto implements ValidacaoCadastroAssunto {
    public ValidacaoCamposNaoPreenchidoAssunto() {
    }

    public void validar(AssuntoDto dto, Long id) {
        if (dto.descricao() == null || dto.descricao().isEmpty()) {
            throw new ValidacaoException("Todos os campos tem que ser preencido");
        }
    }
}
