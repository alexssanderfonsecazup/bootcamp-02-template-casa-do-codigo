package br.com.bootcamp.casaDoCodigo.categoria.controller.dto;

import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaForm {

    @NotBlank
    private String nome;


    public Categoria paraCategoria() {
        return new Categoria(nome);
    }

    public String getNome() {
        return nome;
    }
}
