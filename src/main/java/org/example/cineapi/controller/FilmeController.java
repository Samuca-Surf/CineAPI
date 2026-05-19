package org.example.cineapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.cineapi.dto.AvaliacaoResponseDTO;
import org.example.cineapi.dto.FilmeRequestDTO;
import org.example.cineapi.dto.FilmeResponseDTO;
import org.example.cineapi.model.Filme;
import org.example.cineapi.service.AvaliacaoService;
import org.example.cineapi.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@Tag(name = "Filmes", description = "Endpoints para gerenciamento e consulta de filmes")
public class FilmeController {

    private final FilmeService service;
    private final AvaliacaoService avaliacaoService;

    public FilmeController (FilmeService service, AvaliacaoService avaliacaoService){
        this.service = service;
        this.avaliacaoService = avaliacaoService;
    }

    @Operation(summary = "Listar Filmes", description = "Retorna todos os filmes cadastrados")
    @GetMapping
    public List<FilmeResponseDTO> listar(){
        return service.listar();
    }

    @Operation(summary = "Cadastrar Filmes", description = "Cadastra um novo filme vinculado a um diretor")
    @PostMapping
    public FilmeResponseDTO salvar(@RequestBody @Valid FilmeRequestDTO dto){
        return service.salvar(dto);
    }

    @Operation(summary = "Buscar Filme por id", description = "Permite pesquisar um filme específico pelo id")
    @GetMapping("/{idFilme}")
    public FilmeResponseDTO buscarId(@PathVariable Long idFilme){
        return service.buscarId(idFilme);
    }

    @Operation(summary = "Deletar Filme", description = "Permite deletar um filme específico pelo id")
    @DeleteMapping("/{idFilme}")
    public void deletar(@PathVariable Long idFilme){
        service.deletar(idFilme);
    }

    @Operation(summary = "Atualizar Filme", description = "Permite atualizar informações de um filme específico pelo id")
    @PutMapping("/{idFilme}")
    public FilmeResponseDTO atualizar(@PathVariable Long idFilme, @RequestBody @Valid FilmeRequestDTO dto){
        return service.atualizar(idFilme, dto);
    }

    @Operation(summary = "Mostrar Avaliações do Filme", description = "Retorna as avaliações de um filme específico")
    @GetMapping("/{idFilme}/avaliacoes")
    public List<AvaliacaoResponseDTO> listarAvaliacoesPorFilme(@PathVariable Long idFilme){
        return avaliacaoService.listarPorFilme(idFilme);
    }

    @Operation(summary = "Buscar Filme por Título", description = "Permite pesquisar filmes por título")
    @GetMapping("/buscar")
    public List<FilmeResponseDTO> buscarPorTitulo(@RequestParam String titulo){
        return service.buscarPorTitulo(titulo);
    }

    @Operation(summary = "Buscar Filme por Gênero", description = "Permite pesquisar filmes por gênero")
    @GetMapping("/genero")
    public List<FilmeResponseDTO> buscarPorGenero(@RequestParam String genero){
        return service.buscarPorGenero(genero);
    }




}
