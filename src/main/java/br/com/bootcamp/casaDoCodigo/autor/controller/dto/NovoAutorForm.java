package br.com.bootcamp.casaDoCodigo.autor.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import org.springframework.util.Assert;

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

    public NovoAutorForm(@NotBlank String nome, @NotBlank @Size(max = 400) String descricao, @NotBlank @Email String email) {
        Assert.hasText(nome, "O nome é obrigatório");
        Assert.hasText(descricao,"A descrição é obrigatória");
        Assert.hasText(email, "O email é obrigatório");

        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
    }

    public Autor paraAutor(NovoAutorForm autorForm) {
        return new Autor(nome,descricao,email);
    }

    public String getNome() {
        return nome;
    }


}
