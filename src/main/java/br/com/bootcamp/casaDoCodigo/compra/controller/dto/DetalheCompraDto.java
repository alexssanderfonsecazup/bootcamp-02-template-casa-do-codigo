package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.compra.model.Compra;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public class DetalheCompraDto {


    private String email;
    private String nome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private String pais;
    private String estado;
    private String telefone;
    private String cep;
    private Boolean existeCupom;
    private BigDecimal valorCupom;
    private PedidoDto pedido;
    private BigDecimal valorOriginal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal valorComDesconto;



    public DetalheCompraDto(Compra compra) {
        this.nome = compra.getNome();
        this.email = compra.getEmail();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.estado = compra.getEstado().getNome();
        this.pais = compra.getPais().getNome();
        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.pedido = new PedidoDto(compra.getPedido());
        this.valorOriginal = compra.getPedido().retornaTotalDosItens();
        if (compra.getCupom() != null) {
            this.existeCupom = true;
            this.valorCupom = compra.getCupom().getPercentualNoMomento();
            this.valorComDesconto =compra.getPedido().calculaTotalComDesconto(valorOriginal);
        }else{
            this.existeCupom = false;
            this.valorCupom = null;
        }


    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
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

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public PedidoDto getPedido() {
        return pedido;
    }

    public Boolean getExisteCupom() {
        return existeCupom;
    }

    public BigDecimal getValorCupom() {
        return valorCupom;
    }

    public BigDecimal getValorComDesconto() {
        return valorComDesconto;
    }
}
