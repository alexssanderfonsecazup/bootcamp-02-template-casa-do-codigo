package br.com.bootcamp.casaDoCodigo.categoria.controller.dto;

import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaForm {

    @NotBlank
    @Unique(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public NovaCategoriaForm(@NotBlank String nome) {
        Assert.hasText(nome,"O nome da categoria é obrigatório");
        this.nome = nome;
    }

    public Categoria paraCategoria() {
        return new Categoria(nome);
    }

    public String getNome() {
        return nome;
    }
}
