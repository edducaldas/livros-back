# Livro
Este é um serviço dedicado aos Editais do MPPE desenvolvido em Spring Boot.

## Pré-requisitos

Antes de começar, certifique-se de que você tenha o seguinte instalado:

- Java JDK 17 ou superior
- Maven
- Postgres
- Postman (ou qualquer outra ferramenta de teste de API)

- Com o banco instalado rodar os script:
- CREATE DATABASE LIVRO;
- CREATE OR REPLACE VIEW public.vw_relatorio_livro_autor_assunto
  AS SELECT a.nome AS autor,
  l.titulo AS livro,
  COALESCE(l.editora, ''::character varying) AS editora,
  l.edicao,
  COALESCE(l.ano_publicacao, ''::character varying) AS ano_publicacao,
  COALESCE(s.descricao, ''::character varying) AS assunto
  FROM autores a
  JOIN livro_autor la ON a.id = la.autor_id
  JOIN livros l ON la.livro_id = l.id
  LEFT JOIN livro_assunto las ON l.id = las.livro_id
  LEFT JOIN assuntos s ON las.assunto_id = s.id
  ORDER BY a.nome, l.titulo;