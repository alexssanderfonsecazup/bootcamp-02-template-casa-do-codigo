package br.com.bootcamp.casaDoCodigo.país.controller.dto;

import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;

import javax.validation.constraints.NotBlank;

public class NovoPaisesForm {
    @NotBlank @Unique(domainClass = Pais.class, fieldName = "nome")
    private String nome;


    public Pais toPais(){
        return new Pais(nome);
    }

    public String getNome() {
        return nome;
    }
}
