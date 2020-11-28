package br.com.bootcamp.casaDoCodigo.compra.controller.dto;


import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Exists;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import br.com.bootcamp.casaDoCodigo.compra.model.ItemPedido;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public class NovaCompraItemPedidoForm {

    @NotNull
    @Exists(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;

    @NotNull
    private int quantidade;

    public NovaCompraItemPedidoForm(@NotNull Long idLivro, @NotNull int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public static Set<ItemPedido> paraItensPedido(Set<NovaCompraItemPedidoForm> itensPagamento, EntityManager entityManager) {

        return itensPagamento
                .stream()
                .map(item -> {
                    Livro livro = entityManager.find(Livro.class, item.getIdLivro());
                    return new ItemPedido(livro, item.getQuantidade());
                })
                .collect(Collectors.toSet());

    }

    public Long getIdLivro() {
        return idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
