package br.com.bootcamp.casaDoCodigo.compra.model;

import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.CupomAplicado;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Entity

public class Compra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Embedded
    private CupomAplicado cupom;


    @Deprecated
    /**
     * Construtor usado apenas pelo hibernate
     */
    private Compra(){}

    public Compra(@NotBlank @Email String email, @NotBlank String nome,
                  @NotBlank String documento,
                  @NotBlank String endereco, @NotBlank String complemento,
                  @NotBlank String cidade, @NotNull Pais pais,@NotBlank String telefone,
                  @NotBlank String cep, @NotNull Function<Compra,Pedido> funcaoCriadoraDePedido) {

        this.email = email;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriadoraDePedido.apply(this);

    }

    public void aplicarCupom(Cupom cupom){
        Assert.state(cupom != null,"É necessario um cupom para aplicar o desconto");
        Assert.isNull(this.cupom,"Você nao pode alterar um cupom de uma compra");
        this.cupom = new CupomAplicado(cupom);
    }

    public String getEmail() {
        return email;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setCupom(CupomAplicado cupom) {
        this.cupom = cupom;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public CupomAplicado getCupom() {
        return cupom;
    }


    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }


}
