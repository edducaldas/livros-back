package livro.api.model;

import jakarta.persistence.*;
import livro.api.dto.LivroDto;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Table(name = "livros")
@Entity(name = "Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String editora;

    private long edicao;

    private String anoPublicacao;
    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "assunto_id")
    )
    private Set<Assunto> assuntos = new HashSet<>();

    @Column(precision = 10, scale = 2) // Define até 10 dígitos, com 2 decimais
    private BigDecimal valor;


    public Livro(LivroDto dto) {
        this.titulo = dto.titulo();
        this.editora = dto.editora();
        this.edicao = dto.edicao();
        this.anoPublicacao = dto.anoPublicacao();
        this.valor = dto.valor(); // Adiciona o novo campo
    }

}
