package br.com.bootcamp.casaDoCodigo.livro.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.autor.repository.AutorRepository;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.categoria.repository.CategoriaRepository;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.ExistsId;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class NovoLivroForm {

    @NotBlank
    @Unique(fieldName = "titulo", domainClass = Livro.class)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @DecimalMin("20.0")
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private int numeroDePaginas;

    @NotBlank
    @Unique(fieldName = "isbn", domainClass = Livro.class)
    private String isbn;

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDePublicao;


    @NotNull
    @ExistsId(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Autor.class)
    private Long idAutor;


    public Livro toLivro(EntityManager entityManager){

        Autor autor = entityManager.find(Autor.class, idAutor);
        Assert.state(autor !=null,"Não foi encontrado um autor correspondente cadastrado");

        Categoria categoria = entityManager.find(Categoria.class, idCategoria);

        Assert.state(categoria!=null,"Não foi encontrada uma categoria correspondente cadastrada");

        return new Livro(titulo,resumo,sumario,preco,numeroDePaginas
        ,isbn,dataDePublicao,categoria,autor);
    }


    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDePublicao() {
        return dataDePublicao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }
}
