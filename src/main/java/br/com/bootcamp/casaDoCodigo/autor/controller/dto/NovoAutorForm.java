package br.com.bootcamp.casaDoCodigo.autor.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorForm {

    @NotBlank
    private String nome;

    @NotBlank @Size(max = 400)
    private String descricao;

    @NotBlank @Email
    @Unique(fieldName = "email", domainClass = Autor.class)
    private String email;


    public Autor paraAutor(NovoAutorForm autorForm) {
        return new Autor(nome,descricao,email);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmail() {
        return email;
    }
}
