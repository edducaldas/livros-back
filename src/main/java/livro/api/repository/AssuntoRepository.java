package livro.api.repository;

import livro.api.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuntoRepository extends JpaRepository<Assunto, Long> {
    boolean existsByDescricao(String nome);

     boolean existsByDescricaoAndIdNot(String descricao, Long id);
}
