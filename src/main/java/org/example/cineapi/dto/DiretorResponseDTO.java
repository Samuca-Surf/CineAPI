package org.example.cineapi.dto;

public record DiretorResponseDTO(
        Long idDiretor,
        String nome,
        String nacionalidade,
        Integer idade,
        String biografia
) {}
