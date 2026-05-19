package org.example.cineapi.service;

import org.example.cineapi.dto.FilmeRequestDTO;
import org.example.cineapi.dto.FilmeResponseDTO;
import org.example.cineapi.exception.RecursoNaoEncontradoException;
import org.example.cineapi.model.Avaliacao;
import org.example.cineapi.model.Diretor;
import org.example.cineapi.model.Filme;
import org.example.cineapi.repository.DiretorRepository;
import org.example.cineapi.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {
    private final FilmeRepository repository;
    private final DiretorService diretorService;

    public FilmeService(FilmeRepository repository, DiretorService diretorService){
        this.repository = repository;
        this.diretorService = diretorService;
    }

    public FilmeResponseDTO salvar(FilmeRequestDTO dto){
        Filme filme = toEntity(dto);
        Filme salvo = repository.save(filme);
        return toResponseDTO(salvo);
    }

    public FilmeResponseDTO buscarId(Long idFilme){
        Filme filme = repository.findById(idFilme).orElseThrow(() -> new RecursoNaoEncontradoException("Filme não encontrado"));
        return toResponseDTO(filme);
    }

    public List<FilmeResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void deletar(Long idFilme){
        Filme filme = repository.findById(idFilme).orElseThrow(() -> new RecursoNaoEncontradoException("Filme não encontrado"));
        repository.delete(filme);
    }

    public FilmeResponseDTO atualizar(Long idFilme, FilmeRequestDTO dto){
        Filme existente = repository.findById(idFilme).orElseThrow(() -> new RecursoNaoEncontradoException("Filme não encontrado"));
        Diretor diretor = diretorService.buscarEntidade(dto.idDiretor());
        existente.setTitulo(dto.titulo());
        existente.setGenero(dto.genero());
        existente.setDiretor(diretor);
        existente.setAno(dto.ano());
        existente.setDuracao(dto.duracao());


        Filme atualizado = repository.save(existente);
        return toResponseDTO(atualizado);
    }

    private Filme toEntity(FilmeRequestDTO dto){
        Diretor diretor = diretorService.buscarEntidade(dto.idDiretor());
        Filme filme = new Filme();
        filme.setTitulo(dto.titulo());
        filme.setGenero(dto.genero());
        filme.setDiretor(diretor);
        filme.setAno(dto.ano());
        filme.setDuracao(dto.duracao());

        return filme;
    }

    private FilmeResponseDTO toResponseDTO(Filme filme){
        return new FilmeResponseDTO(
                filme.getIdFilme(),
                filme.getTitulo(),
                filme.getGenero(),
                filme.getAno(),
                filme.getDuracao(),
                filme.getDiretor().getIdDiretor(),
                filme.getDiretor().getNome(),
                calcularMediaAvaliacoes(filme),
                contarAvaliacoes(filme)
        );
    }

    public List<FilmeResponseDTO> listarFilmesPorDiretor(Long idDiretor){
        diretorService.buscarEntidade(idDiretor);
        return repository.findByDiretorIdDiretor(idDiretor)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private Double calcularMediaAvaliacoes(Filme filme){
        if (filme.getAvaliacoes() == null || filme.getAvaliacoes().isEmpty()){
            return 0.0;
        }

        return filme.getAvaliacoes()
                .stream()
                .mapToInt(Avaliacao::getNota)
                .average().orElse(0.0);
    }

    private Integer contarAvaliacoes(Filme filme){
        if(filme.getAvaliacoes() == null || filme.getAvaliacoes().isEmpty()){
            return 0;
        }

        return filme.getAvaliacoes().size();
    }

    public List<FilmeResponseDTO> buscarPorTitulo(String titulo){
        return repository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<FilmeResponseDTO> buscarPorGenero(String genero){
        return repository.findByGeneroContainingIgnoreCase(genero)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
