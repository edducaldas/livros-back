package livro.api.validacoes.assunto;

import livro.api.dto.AssuntoDto;

public interface ValidacaoCadastroAssunto {
    void validar(AssuntoDto dto, Long id);

}
