package livro.api.service;


import jakarta.persistence.EntityNotFoundException;
import livro.api.dto.LivroDto;
import livro.api.model.Assunto;
import livro.api.model.Autor;

import livro.api.model.Livro;
import livro.api.repository.AssuntoRepository;
import livro.api.repository.AutorRepository;

import livro.api.repository.LivroRepository;
import livro.api.validacoes.livros.ValidacaoCadastroLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;
    @Autowired
    private List<ValidacaoCadastroLivro> validacoes;

    @Transactional
    public void cadastrar(LivroDto dto) {
        validacoes.forEach(v -> v.validar(dto, null));

        Set<Autor> autores = dto.autores().stream()
                .map(id -> autorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com ID: " + id)))
                .collect(Collectors.toSet()); // Converte o Stream em um Set

        Set<Assunto> assuntos = dto.assuntos().stream()
                .map(id -> assuntoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Assunto não encontrado com ID: " + id)))
                .collect(Collectors.toSet());

        Livro livro = new Livro(dto);
        livro.setAutores(autores);
        livro.setAssuntos(assuntos);
        repository.save(livro);
    }

    public List<LivroDto> getAllLivros() {
        return repository.findAll()
                .stream()
                .map(LivroDto::new) // Converte cada Livro para LivroDto
                .collect(Collectors.toList());
    }

    @Transactional
    public LivroDto alterar(Long id, LivroDto dto) {
        validacoes.forEach(v -> v.validar(dto, id));
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));

        livro.setTitulo(dto.titulo());
        livro.setEditora(dto.editora());
        livro.setEdicao(dto.edicao());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setValor(dto.valor());

        Set<Autor> autores = dto.autores().stream()
                .map(autorId -> autorRepository.findById(autorId)
                        .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com ID: " + autorId)))
                .collect(Collectors.toSet());

        Set<Assunto> assuntos = dto.assuntos().stream()
                .map(assuntoId -> assuntoRepository.findById(assuntoId)
                        .orElseThrow(() -> new EntityNotFoundException("Assunto não encontrado com ID: " + assuntoId)))
                .collect(Collectors.toSet());

        livro.setAutores(autores);
        livro.setAssuntos(assuntos);
        livro = repository.save(livro);
        return new LivroDto(livro);
    }

    @Transactional
    public void deleteLivro(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));
        repository.delete(livro);
    }

    public LivroDto getLivroById(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));
        return new LivroDto(livro);
    }


}
