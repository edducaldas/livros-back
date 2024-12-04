package livro.api.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import livro.api.dto.AssuntoDto;
import livro.api.dto.AutorDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "assuntos")
@Entity(name = "Assunto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros = new HashSet<>();

    public Assunto(AssuntoDto dto) {
        this.descricao = dto.descricao();

    }
}
