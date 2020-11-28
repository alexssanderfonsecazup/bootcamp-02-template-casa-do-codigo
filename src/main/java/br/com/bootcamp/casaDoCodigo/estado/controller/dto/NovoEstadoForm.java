package br.com.bootcamp.casaDoCodigo.estado.controller.dto;

import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Exists;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoForm {

    @NotNull @Exists(fieldName = "id", domainClass = Pais.class)
    private Long idPais;

    @NotBlank @Unique(domainClass = Estado.class , fieldName = "nome")
    private String nome;

    public Estado toEstado(EntityManager entityManager){

        Pais pais = entityManager.find(Pais.class, idPais);
        Assert.state(pais !=null,"Não foi encontrado um país correspondente cadastrado");

        return new Estado(pais,nome);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
