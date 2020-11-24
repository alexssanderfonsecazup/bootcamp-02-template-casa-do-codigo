package br.com.bootcamp.casaDoCodigo.pagamento.controller.dto;

import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.ExistsId;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoPagamentoPrimeiraEtapaForm {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long pais;

    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Long estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;




    public boolean documentoValido() {
        Assert.hasText(documento,"Documento não pode estar em branco");
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cnpjValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }


    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getPais() {
        return pais;
    }

    public Long getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

}
