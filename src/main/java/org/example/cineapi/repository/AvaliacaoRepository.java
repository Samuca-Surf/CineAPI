package org.example.cineapi.repository;

import org.example.cineapi.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByFilmeIdFilme(Long idFilme);
}
