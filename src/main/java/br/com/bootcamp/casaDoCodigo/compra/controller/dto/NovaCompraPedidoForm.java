package br.com.bootcamp.casaDoCodigo.compra.controller.dto;


import br.com.bootcamp.casaDoCodigo.compra.model.Compra;
import br.com.bootcamp.casaDoCodigo.compra.model.ItemPedido;
import br.com.bootcamp.casaDoCodigo.compra.model.Pedido;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;

public class NovaCompraPedidoForm {

    @NotNull
    @Positive
    private BigDecimal total;

    @NotNull
    @Size(min = 1, message = "O carrinho não pode estar vazio")
    @Valid
    private Set<NovaCompraItemPedidoForm> itens;

    public NovaCompraPedidoForm(@NotNull @Positive BigDecimal total,
                                @NotNull @Size(min = 1) @Valid Set<NovaCompraItemPedidoForm> itens) {
        this.total = total;
        this.itens = itens;
    }

    public Function<Compra,Pedido> paraPedido(EntityManager entityManager) {
        Set<ItemPedido> itensPedido = NovaCompraItemPedidoForm.paraItensPedido(itens, entityManager);

        return (compra) -> {
            Pedido pedido = new  Pedido(compra, itensPedido);
            Assert.isTrue(pedido.retornaTotalDosItens().compareTo(total) ==0,"O valor não corresponde ao valor esperado") ;
            return pedido;
        };


    }


    public BigDecimal getTotal() {
        return total;
    }

    public Set<NovaCompraItemPedidoForm> getItens() {
        return itens;
    }


}
