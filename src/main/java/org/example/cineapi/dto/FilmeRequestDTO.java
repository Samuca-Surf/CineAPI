package org.example.cineapi.dto;

import jakarta.validation.constraints.*;

public record FilmeRequestDTO(
        @NotBlank(message = "Preencha o título")
        String titulo,
        @NotBlank(message = "Preencha o gênero")
        String genero,
        @NotNull(message = "Preencha o diretor")
        Long idDiretor,
        @NotNull(message = "Preencha o ano")
        @Min(value = 1895, message = "O ano deve ser maior que 1895")
        @Max(value = 2026, message = "O ano deve ser 2026 ou menor")
        Integer ano,
        @NotNull(message = "Preencha a duração")
        @Positive
        Integer duracao
) {}
