package org.example.cineapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Diretor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiretor;

    private String nome;
    private String nacionalidade;
    private Integer idade;
    private String biografia;

    @OneToMany(mappedBy = "diretor")
    private List<Filme> filmes = new ArrayList<>();

    public Diretor(){}
}
