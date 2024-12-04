package livro.api.validacoes.assunto;

import livro.api.dto.AssuntoDto;
import livro.api.exception.ValidacaoException;
import livro.api.repository.AssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoAssuntoJaExiste implements ValidacaoCadastroAssunto {
    @Autowired
    private AssuntoRepository repository;

    public ValidacaoAssuntoJaExiste() {
    }

    public void validar(AssuntoDto dto, Long id) {
        // Verifica se já existe outro registro com a mesma descrição
        boolean nomeJaCadastrado = id == null
                ? repository.existsByDescricao(dto.descricao())
                : repository.existsByDescricaoAndIdNot(dto.descricao(), id);


        if (nomeJaCadastrado) {
            throw new ValidacaoException("Nome já cadastrado");
        }
    }
}