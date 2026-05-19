package org.example.cineapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequestDTO(
        @NotNull(message = "Preencha a nota")
        @Min(value = 0, message = "Nota mínima é 0")
        @Max(value = 5, message = "Nota máxima é 5")
        Integer nota,

        String comentario,

        @NotNull(message = "Preencha o filme")
        Long idFilme
) {
}
