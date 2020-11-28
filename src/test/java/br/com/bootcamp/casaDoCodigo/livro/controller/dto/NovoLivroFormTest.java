package br.com.bootcamp.casaDoCodigo.livro.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroFormTest {


    @Test()
    @DisplayName("Deveria cria o livro")
    public void deveriaCriarLivroSemAutor(){


        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.when(entityManager.find(Autor.class,1l))
                .thenReturn(new Autor("teste","teste","teste"));

        Mockito.when(entityManager.find(Categoria.class,1))
                .thenReturn(new Categoria("teste"));

        NovoLivroForm novoLivroForm = new NovoLivroForm("teste","teste","teste", BigDecimal.valueOf(20.00),120,"", LocalDate.now(),1l,1l);

        Assertions.assertNotNull(novoLivroForm.toLivro(entityManager));
    }



    @Test()
    @DisplayName("Não deveria criar livro sem autor")
    public void naoDeveriaCriarLivroSemAutor(){


        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.when(entityManager.find(Autor.class,1))
                .thenReturn(null);

        NovoLivroForm novoLivroForm = new NovoLivroForm("teste","teste","teste", BigDecimal.valueOf(20.00),120,"", LocalDate.now(),1l,null);

        Assertions.assertThrows(IllegalStateException.class,()-> novoLivroForm.toLivro(entityManager));
    }

    @Test()
    @DisplayName("Não deveria criar livro sem categoria")
    public void naoDeveriaCriarLivroSemCategoria(){


        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.when(entityManager.find(Autor.class,1))
                .thenReturn(null);

        NovoLivroForm novoLivroForm = new NovoLivroForm("teste","teste","teste", BigDecimal.valueOf(20.00),120,"", LocalDate.now(),null,1l);

        Assertions.assertThrows(IllegalStateException.class,()-> novoLivroForm.toLivro(entityManager));
    }

}
