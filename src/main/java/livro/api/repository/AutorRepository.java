package livro.api.repository;

import livro.api.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Long id);
}
