package livro.api.repository;

import livro.api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository  extends JpaRepository<Livro, Long> {

    boolean existsByTitulo(String titulo);

    boolean existsByTituloAndIdNot(String titulo, Long id);
}
