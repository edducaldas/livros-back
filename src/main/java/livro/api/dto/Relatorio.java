package livro.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relatorio {
    private String autor;
    private String livro;
    private String editora;
    private Long edicao;
    private String ano_publicacao;
    private String assunto;



    public Relatorio(String autor, String livro, String editora, Long edicao, String ano_publicacao, String assunto) {
        this.autor = autor;
        this.livro = livro;
        this.editora = editora;
        this.edicao = edicao;
        this.ano_publicacao = ano_publicacao;
        this.assunto = assunto;

    }
}
