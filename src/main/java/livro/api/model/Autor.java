package livro.api.model;

import jakarta.persistence.*;
import livro.api.dto.AutorDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public Autor(AutorDto dto) {
        this.nome = dto.nome();

    }
}
