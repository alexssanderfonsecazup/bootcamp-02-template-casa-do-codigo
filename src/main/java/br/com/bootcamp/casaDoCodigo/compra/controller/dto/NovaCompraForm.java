package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Exists;
import br.com.bootcamp.casaDoCodigo.compra.model.Compra;
import br.com.bootcamp.casaDoCodigo.compra.model.Pedido;
import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import br.com.bootcamp.casaDoCodigo.cupom.repository.CupomRepository;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

public class NovaCompraForm {

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
    @Exists(domainClass = Pais.class, fieldName = "id")
    private Long pais;

    @Exists(domainClass = Estado.class, fieldName = "id")
    private Long estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull @Valid
    private NovaCompraPedidoForm pedido;

    private String codigoCupom;


    public NovaCompraForm(@NotBlank @Email String email, @NotBlank String nome,
                          @NotBlank String documento, @NotBlank String endereco,
                          @NotBlank String complemento, @NotBlank String cidade,
                          @NotNull Long pais, @NotBlank String telefone,
                          @NotBlank String cep, @NotNull @Valid NovaCompraPedidoForm pedido) {
        this.email = email;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
    }

    public boolean documentoValido() {
        Assert.hasText(documento,"Documento não pode estar em branco");
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }

    public Compra paraCompra(EntityManager entityManager, CupomRepository cupomRepository){

        Pais pais = entityManager.find(Pais.class, this.pais);
        Function<Compra,Pedido> funcaoCriacaoPedido = pedido.paraPedido(entityManager);

        Compra compra = new Compra(email,nome,documento,endereco,complemento,cidade,pais,telefone,cep,funcaoCriacaoPedido);
        if(this.estado != null){
            Estado estado = entityManager.find(Estado.class, this.estado);
            compra.setEstado(estado);
        }

        if(codigoCupom!=null){
            Cupom cupom = cupomRepository.getByCodigo(codigoCupom);
            compra.aplicarCupom(cupom);
        }
        return compra;

    }

    public Boolean possuiEstado(){
        return estado !=null;
    }


    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public void setEstado(Long estado) {
        Assert.state(estado!=null,"Não e possivél setar um estado nulo");
        this.estado = estado;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public String getNome() {
        return nome;
    }

    public Long getPais() {
        return pais;
    }

    public Long getEstado() {
        return estado;
    }

    public NovaCompraPedidoForm getPedido() {
        return pedido;
    }


    public String getEmail() {
        return email;
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

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
