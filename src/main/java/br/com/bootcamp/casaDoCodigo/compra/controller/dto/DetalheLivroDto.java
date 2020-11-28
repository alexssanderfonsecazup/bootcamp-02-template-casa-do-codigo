package br.com.bootcamp.casaDoCodigo.compra.controller.dto;

import br.com.bootcamp.casaDoCodigo.livro.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalheLivroDto {

    private String titulo;
    private String isbn;
    private LocalDate dataPublicacao;
    private BigDecimal preco;
    private AutorDto autor;

    public DetalheLivroDto(Livro livro){
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataDePublicacao();
        this.preco = livro.getPreco();
        this.autor = new AutorDto(livro.getAutor());

    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public AutorDto getAutor() {
        return autor;
    }
}
