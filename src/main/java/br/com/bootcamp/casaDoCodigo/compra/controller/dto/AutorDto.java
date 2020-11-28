package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;

public class AutorDto {
    private String nome;

    public AutorDto(Autor autor){
        this.nome = autor.getNome();
    }

    public String getNome() {
        return nome;
    }
}
