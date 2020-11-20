package br.com.bootcamp.casaDoCodigo.categoria.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "nome_unico", columnNames = {"nome"}) })
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;

    /**
     * Construtor
     */
    @Deprecated
    public Categoria(){

    }


    public Categoria(@NotBlank String nome) {
        Assert.hasText(nome,"Nome da categoria é obrigatória");
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
