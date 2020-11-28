package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.compra.model.Compra;
import br.com.bootcamp.casaDoCodigo.compra.model.Pedido;
import br.com.bootcamp.casaDoCodigo.livro.builder.BuilderLivro;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class NovaCompraPedidoFormTest {

    private Set<NovaCompraItemPedidoForm> itens = new HashSet<NovaCompraItemPedidoForm>(Arrays.asList(new NovaCompraItemPedidoForm(1l, 10)
            , new NovaCompraItemPedidoForm(2l, 10)));


    EntityManager entityManager = null;

    @BeforeEach
    public void inicializar() {
        entityManager = Mockito.mock(EntityManager.class);
        Livro livro1 = new BuilderLivro()
                .addAutor(new Autor("Teste", "Teste", "teste@teste.com"))
                .addCategoria(new Categoria("teste"))
                .addAutor(new Autor("Teste", "Teste", "teste@teste.com"))
                .addCategoria(new Categoria("teste"))
                .addDataDePublicacao(LocalDate.now())
                .addIsbn("ds5a4d45asd45a")
                .addPreco(BigDecimal.valueOf(20.00))
                .addNumeroDePaginas(500)
                .addSumario("#blablabla")
                .build();

        Livro livro2 = new BuilderLivro()
                .addAutor(new Autor("Teste", "Teste", "teste@teste.com"))
                .addCategoria(new Categoria("teste"))
                .addAutor(new Autor("Teste", "Teste", "teste@teste.com"))
                .addCategoria(new Categoria("teste"))
                .addDataDePublicacao(LocalDate.now())
                .addIsbn("asdda123123")
                .addPreco(BigDecimal.valueOf(40.00))
                .addNumeroDePaginas(500)
                .addSumario("#blablabla")
                .build();


        Mockito.when(entityManager.find(Livro.class, 1l))
                .thenReturn(livro1);
        Mockito.when(entityManager.find(Livro.class, 2l))
                .thenReturn(livro2);

    }



    @Test
    public void deveCriarPedido(){
        NovaCompraPedidoForm pedido = new NovaCompraPedidoForm(BigDecimal.valueOf(600.00), itens);
        Assertions.assertNotNull(pedido.paraPedido(entityManager));
    }

    @Test
    public void naoDeveCriarPedido(){
        NovaCompraPedidoForm pedido = new NovaCompraPedidoForm(BigDecimal.valueOf(1250.00), itens);
        Function<Compra, Pedido> functionCriaPedido = pedido.paraPedido(entityManager);
         Assertions.assertThrows(IllegalArgumentException.class,()->{
             new Compra("teste@teste.com", "Teste", "1467894594", "Teste", "Teste",
                     "Teste", new Pais("Br"), "1111-4444", "3333-111", functionCriaPedido);

         });
    }
}
