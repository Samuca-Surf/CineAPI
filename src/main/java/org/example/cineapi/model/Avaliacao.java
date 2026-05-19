package org.example.cineapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao;

    private Integer nota;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "idFilme")
    private Filme filme;

    public Avaliacao(){}
}
