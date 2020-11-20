package br.com.bootcamp.casaDoCodigo.autor.model;



import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "email_unico", columnNames = {"email"}) })

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final LocalDateTime dataCriacao = LocalDateTime.now();

    @NotBlank
    private String nome;

    @NotBlank @Size(max = 400)
    private String descricao;

    @NotBlank @Email
    private String email;

    /**
     * Este construtor deve ser usado apenas pelo hibernate
     */
    @Deprecated
    public Autor(){ }

    public Autor(@NotBlank  String nome,
                 @NotBlank @Size(max=400) String descricao,
                 @NotBlank @Email String email){

        Assert.hasText(nome,"O nome é obrigatorio");
        Assert.hasText(descricao,"A descrição é obrigatoria");
        Assert.hasText(email,"O email não pode ser nulo");

        this.nome = nome;
        this.descricao = descricao;
        this.email = email;

    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
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
