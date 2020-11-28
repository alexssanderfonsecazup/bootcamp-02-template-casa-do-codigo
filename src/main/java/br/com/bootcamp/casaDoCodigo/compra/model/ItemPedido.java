package br.com.bootcamp.casaDoCodigo.compra.model;

import br.com.bootcamp.casaDoCodigo.compra.controller.dto.DetalheLivroDto;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {

    @ManyToOne
    private Livro livro;
    private int quantidade;
    @Positive
    private BigDecimal precoMomento;

    /**
     * Construtor usado pelo hibernate
     */
    @Deprecated
    public ItemPedido(){

    }


    public ItemPedido(@NotNull Livro livro, @NotNull @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return livro.equals(that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }

    public BigDecimal valorTotalDoItem() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    public Livro getLivro() {
        return this.livro;
    }

    public int getQuantidade() {
        return this.quantidade;
    }
}
