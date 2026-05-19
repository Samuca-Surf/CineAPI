package org.example.cineapi.dto;

public record FilmeResponseDTO(
        Long idFilme,
        String titulo,
        String genero,
        Integer ano,
        Integer duracao,
        Long idDiretor,
        String nome,
        Double mediaAvaliacoes,
        Integer quantidadeAvaliacoes
) {}
