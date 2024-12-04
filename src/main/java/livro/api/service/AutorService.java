package livro.api.service;

import jakarta.persistence.EntityNotFoundException;
import livro.api.dto.AutorDto;
import livro.api.model.Autor;
import livro.api.repository.AutorRepository;
import livro.api.validacoes.autor.ValidacaoCadastroAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    @Autowired
    private AutorRepository repository;

    @Autowired
    private List<ValidacaoCadastroAutor> validacoes;

    @Transactional
    public void cadastrar(AutorDto dto) {
        validacoes.forEach(v -> v.validar(dto,null));
        Autor autor = new Autor(dto);
        repository.save(autor);
    }

    public List<AutorDto> getAllAutores() {
        return repository.findAll()
                .stream()
                .map(AutorDto::new) // Converte cada Livro para LivroDto
                .collect(Collectors.toList());
    }
    @Transactional
    public AutorDto alterar(Long id, AutorDto dto) {
        validacoes.forEach(v -> v.validar(dto, id));
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));

        autor.setNome(dto.nome());

        autor = repository.save(autor);
        return new AutorDto(autor);
    }
    @Transactional
    public void delete(Long id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));
        repository.delete(autor);
    }

    public AutorDto getAutorById(Long id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));
        return new AutorDto(autor);
    }
}
