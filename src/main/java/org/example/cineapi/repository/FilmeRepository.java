package org.example.cineapi.repository;

import org.example.cineapi.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository <Filme, Long> {
    List <Filme> findByTituloContainingIgnoreCase(String titulo);
    List<Filme> findByDiretorIdDiretor(Long idDiretor);
    List<Filme> findByGeneroContainingIgnoreCase(String genero);
}
