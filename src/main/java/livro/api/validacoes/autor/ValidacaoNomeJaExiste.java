package livro.api.validacoes.autor;

import livro.api.dto.AutorDto;
import livro.api.exception.ValidacaoException;
import livro.api.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoNomeJaExiste implements ValidacaoCadastroAutor  {
    @Autowired
    private AutorRepository repository;

    public ValidacaoNomeJaExiste() {
    }

    public void validar(AutorDto dto, Long id) {

        boolean nomeJaCadastrado = id == null
                ? repository.existsByNome(dto.nome())
                : repository.existsByNomeAndIdNot(dto.nome(), id);
        if (nomeJaCadastrado) {
            throw new ValidacaoException("Nome já cadastrado");
        }
    }
}
