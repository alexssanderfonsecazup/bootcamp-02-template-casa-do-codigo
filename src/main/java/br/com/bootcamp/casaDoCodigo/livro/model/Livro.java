package br.com.bootcamp.casaDoCodigo.livro.model;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
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
    private String isbn;

    @Future
    private LocalDate dataDePublicao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Autor autor;

    @Deprecated
    public Livro(){ }


    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                 String sumario, @NotNull @DecimalMin("20.0") BigDecimal preco,
                 @NotNull @Min(100) int numeroDePaginas, @NotBlank String isbn,
                 @Future LocalDate dataDePublicao, @NotNull Categoria categoria,
                 @NotNull Autor autor) {


        this.categoria = categoria;
        this.autor = autor;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicao = dataDePublicao;

    }

    public Long getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
