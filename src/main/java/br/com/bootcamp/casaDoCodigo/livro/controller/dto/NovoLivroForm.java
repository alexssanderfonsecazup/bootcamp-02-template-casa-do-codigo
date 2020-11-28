package br.com.bootcamp.casaDoCodigo.livro.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Exists;
import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import br.com.bootcamp.casaDoCodigo.livro.builder.BuilderLivro;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate dataDePublicacao;


    @NotNull
    @Exists(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @NotNull
    @Exists(fieldName = "id", domainClass = Autor.class)
    private Long idAutor;


    public NovoLivroForm(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                         String sumario, @NotNull @DecimalMin("20.0") BigDecimal preco,
                         @NotNull @Min(100) int numeroDePaginas,
                         @NotBlank String isbn, @Future LocalDate dataDePublicacao,
                         @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro toLivro(EntityManager entityManager){

        Autor autor = entityManager.find(Autor.class, idAutor);
        Assert.state(autor !=null,"Não foi encontrado um autor correspondente cadastrado");

        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        Assert.state(categoria!=null,"Não foi encontrada uma categoria correspondente cadastrada");

        return new BuilderLivro().addAutor(autor)
                .addTitulo(titulo)
                .addResumo(resumo)
                .addCategoria(categoria)
                .addPreco(preco)
                .addNumeroDePaginas(numeroDePaginas)
                .addAutor(autor)
                .addIsbn(isbn)
                .addSumario(sumario)
                .addDataDePublicacao(dataDePublicacao)
                .build();
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

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }
}
