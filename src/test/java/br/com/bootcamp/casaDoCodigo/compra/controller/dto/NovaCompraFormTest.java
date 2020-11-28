package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import br.com.bootcamp.casaDoCodigo.cupom.repository.CupomRepository;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.livro.builder.BuilderLivro;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NovaCompraFormTest {


    private Set<NovaCompraItemPedidoForm> itens = new HashSet<NovaCompraItemPedidoForm>(Arrays.asList(new NovaCompraItemPedidoForm(1l, 10)
            , new NovaCompraItemPedidoForm(2l, 10)));
    private NovaCompraPedidoForm pedido = new NovaCompraPedidoForm(BigDecimal.valueOf(600.00), itens);


    EntityManager entityManager = null;
    CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);


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
        Mockito.when(entityManager.find(Pais.class, 1l))
                .thenReturn(new Pais("Brasil"));
        Mockito.when(entityManager.find(Estado.class, 1l))
                .thenReturn(new Estado(new Pais("BR"), "MG"));
        Mockito.when(cupomRepository.getByCodigo("TESTETOP")).thenReturn(new Cupom("TESTETOP", BigDecimal.valueOf(0.3), LocalDateTime.now().plusMinutes(10)));
    }


    @Test
    @DisplayName("Deve criar compra com estado e cupom preenchidos")
    public void deveCriarNovaCompra() {
         NovaCompraForm compra = new NovaCompraForm("teste@teste.com", "Teste", "1467894594", "Teste", "Teste",
                "Teste", 1l, "1111-4444", "3333-111", pedido);
        compra.setEstado(1l);
        compra.setCodigoCupom("TESTETOP");
        compra.paraCompra(entityManager, cupomRepository);
        Mockito.verify(entityManager).find(Estado.class, 1l);
        Mockito.verify(cupomRepository).getByCodigo("TESTETOP");

    }

    @Test
    @DisplayName("Deve criar compra com estado e cupom vazios")
    public void deveCriarNovaCompraComEstadoECumpomNull() {
        NovaCompraForm compra = new NovaCompraForm("teste@teste.com", "Teste", "1467894594", "Teste", "Teste",
                "Teste", 1l, "1111-4444", "3333-111", pedido);
        Mockito.verify(entityManager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
        Mockito.verify(cupomRepository, Mockito.never()).getByCodigo(Mockito.anyString());
    }

    @Test
    @DisplayName("Deve criar compra com estado e cupom vazios")
    public void deveCriarNovaCompraSemEstadoECupom() {
        NovaCompraForm compra = new NovaCompraForm("teste@teste.com", "Teste", "1467894594", "Teste", "Teste",
                "Teste", 1l, "1111-4444", "3333-111", pedido);
        Mockito.verify(entityManager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
        Mockito.verify(cupomRepository, Mockito.never()).getByCodigo(Mockito.anyString());

    }

    @ParameterizedTest
    @DisplayName("Deve verificar se o documento é valído")
    @CsvSource({
            "66880182097,true",
            "24124601000174,true",
            "6454426153641,false"
    })
    public void testeValidacaoDocumento(String documento, boolean resposta) {
        NovaCompraForm compra = new NovaCompraForm("teste@teste.com", "Teste", documento, "Teste", "Teste",
                "Teste", 1l, "1111-4444", "3333-111", pedido);
        Assertions.assertEquals(resposta,compra.documentoValido());
    }

}
