package br.com.bootcamp.casaDoCodigo.compra.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Compra compra;


    @ElementCollection
    private Set<ItemPedido> itens = new HashSet<>();

    /**
     * Construtor usado apenas pelo hibernate
     */
    @Deprecated
    public Pedido(){}


    public Pedido(@NotNull @Valid Compra compra, @NotNull  @Size(min = 1) Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(),"E necessário pelo menos um item no pedido");
        this.compra = compra;
        this.itens = itens;
    }

    public BigDecimal retornaTotalDosItens(){
        BigDecimal totalDoPedido = itens.stream()
                .map(ItemPedido::valorTotalDoItem)
                .reduce(BigDecimal.ZERO, (subtotal, elemento) -> subtotal.add(elemento));
        return totalDoPedido;

    }

    public BigDecimal calculaTotalComDesconto(BigDecimal total){
        BigDecimal percentual = compra.getCupom().getPercentualNoMomento();
        return total.subtract(total.multiply(percentual)).setScale(2);

    }

    public Set<ItemPedido> getItens() {
        return itens;
    }
}
