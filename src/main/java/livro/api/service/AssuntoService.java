package livro.api.service;

import livro.api.dto.AssuntoDto;
import livro.api.model.Assunto;
import livro.api.repository.AssuntoRepository;
import livro.api.validacoes.assunto.ValidacaoCadastroAssunto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssuntoService {
    @Autowired
    private AssuntoRepository repository;

    @Autowired
    private List<ValidacaoCadastroAssunto> validacoes;
    @Transactional
    public void cadastrar(AssuntoDto dto) {
        validacoes.forEach(v -> v.validar(dto,null));
        Assunto assunto = new Assunto(dto);
        repository.save(assunto);
    }

    public List<AssuntoDto> getAllAutores() {
        return repository.findAll()
                .stream()
                .map(AssuntoDto::new) // Converte cada Livro para LivroDto
                .collect(Collectors.toList());
    }

    @Transactional
    public AssuntoDto alterar(Long id, AssuntoDto dto) {
        validacoes.forEach(v -> v.validar(dto, id));
        Assunto assunto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assunto não encontrado!"));

        assunto.setDescricao(dto.descricao());
        assunto = repository.save(assunto);
        return new AssuntoDto(assunto);
    }
    @Transactional
    public void delete(Long id) {
        Assunto assunto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assunto não encontrado!"));
        repository.delete(assunto);
    }

    public AssuntoDto getAssuntoById(Long id) {
        Assunto assunto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assunto não encontrado!"));
        return new AssuntoDto(assunto);
    }
}