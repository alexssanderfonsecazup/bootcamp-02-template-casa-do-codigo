package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.compra.model.ItemPedido;

public class ItemPedidoDto {

    private DetalheLivroDto livro;
    private int quantidade;

    public ItemPedidoDto(ItemPedido itemPedido){
        this.livro = new DetalheLivroDto(itemPedido.getLivro());
        this.quantidade = itemPedido.getQuantidade();
    }


    public DetalheLivroDto getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
