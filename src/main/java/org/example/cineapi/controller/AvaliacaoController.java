package org.example.cineapi.controller;

import jakarta.validation.Valid;
import org.example.cineapi.dto.AvaliacaoRequestDTO;
import org.example.cineapi.dto.AvaliacaoResponseDTO;
import org.example.cineapi.model.Avaliacao;
import org.example.cineapi.service.AvaliacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service){
        this.service = service;
    }

    @PostMapping
    public AvaliacaoResponseDTO salvar(@RequestBody @Valid AvaliacaoRequestDTO dto){
        return service.salvar(dto);
    }
}
