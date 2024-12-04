package livro.api.validacoes.livros;

import livro.api.dto.LivroDto;
import livro.api.exception.ValidacaoException;
import livro.api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTituloJaExiste  implements ValidacaoCadastroLivro  {
    @Autowired
    private LivroRepository repository;

    public ValidacaoTituloJaExiste() {
    }

    public void validar(LivroDto dto, Long id) {

        boolean tituloJaCadastrado = id == null
                ? repository.existsByTitulo(dto.titulo())
                : repository.existsByTituloAndIdNot(dto.titulo(), id);
        if (tituloJaCadastrado) {
            throw new ValidacaoException("Titulo já cadastrado");
        }
    }

}
