package org.example.cineapi.service;

import org.example.cineapi.dto.AvaliacaoRequestDTO;
import org.example.cineapi.dto.AvaliacaoResponseDTO;
import org.example.cineapi.exception.RecursoNaoEncontradoException;
import org.example.cineapi.model.Avaliacao;
import org.example.cineapi.model.Filme;
import org.example.cineapi.repository.AvaliacaoRepository;
import org.example.cineapi.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final FilmeRepository filmeRepository;

    public AvaliacaoService(AvaliacaoRepository repository, FilmeRepository filmeRepository){
        this.repository = repository;
        this.filmeRepository = filmeRepository;
    }

    private AvaliacaoResponseDTO toResponseDTO(Avaliacao avaliacao){
        return new AvaliacaoResponseDTO(
                avaliacao.getIdAvaliacao(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getFilme().getIdFilme(),
                avaliacao.getFilme().getTitulo()
        );
    }

    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto){
        Filme filme = filmeRepository.findById(dto.idFilme())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filme não encontrado"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setFilme(filme);

        Avaliacao salva = repository.save(avaliacao);
        return toResponseDTO(salva);
    }

    public List<AvaliacaoResponseDTO> listarPorFilme(Long idFilme){
        filmeRepository.findById(idFilme)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filme não encontrado"));

        return repository.findByFilmeIdFilme(idFilme)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
