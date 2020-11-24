package br.com.bootcamp.casaDoCodigo.livro.builder;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BuilderLivro {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private int numeroDePaginas;
    private String isbn;
    private LocalDate dataDePublicacao;
    private Categoria categoria;
    private Autor autor;

    public BuilderLivro addTitulo(String titulo){
        this.titulo = titulo;
        return this;
    }
    public BuilderLivro addResumo(String resumo){
        this.resumo = resumo;
        return this;
    }

    public BuilderLivro addSumario(String sumario){
        this.sumario = sumario;
        return this;
    }

    public BuilderLivro addPreco(BigDecimal preco){
        this.preco = preco;
        return this;
    }

    public BuilderLivro addNumeroDePaginas(int numeroDePaginas){
        this.numeroDePaginas = numeroDePaginas;
        return this;
    }

    public BuilderLivro addCategoria(Categoria categoria){
        this.categoria = categoria;
        return this;
    }

    public BuilderLivro addAutor(Autor autor){
        this.autor = autor;
        return this;
    }

    public BuilderLivro addIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BuilderLivro addDataDePublicacao(LocalDate dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
        return this;
    }

    public Livro build(){
        return new Livro(this);
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
