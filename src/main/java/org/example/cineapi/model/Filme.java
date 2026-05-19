package org.example.cineapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilme;
    private String titulo;
    private String genero;

    private Integer ano;
    private Integer duracao;


    @ManyToOne
    @JoinColumn(name = "idDiretor")
    private Diretor diretor;

    @OneToMany(mappedBy = "filme")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Filme(){}
}
