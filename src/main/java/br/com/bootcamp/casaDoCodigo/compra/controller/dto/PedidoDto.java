package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.compra.model.Pedido;

import java.util.Set;
import java.util.stream.Collectors;

public class PedidoDto {
    private Set<ItemPedidoDto> itens;

    public PedidoDto(Pedido pedido){
        this.itens = pedido.getItens()
                .stream()
                .map(ItemPedidoDto::new)
                .collect(Collectors.toSet());
    }

    public Set<ItemPedidoDto> getItens() {
        return itens;
    }
}
