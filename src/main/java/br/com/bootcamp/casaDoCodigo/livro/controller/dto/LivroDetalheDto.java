package br.com.bootcamp.casaDoCodigo.livro.controller.dto;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LivroDetalheDto {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private int numeroDePaginas;
    private LocalDate dataDePublicacao;
    private String categoria;
    private Map<String, Object> autor = new HashMap<>();
    private String isbn;


    public LivroDetalheDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.dataDePublicacao = livro.getDataDePublicacao();
        this.isbn = livro.getIsbn();
        this.categoria = livro.getCategoria().getNome();
        this.autor.put("nome", livro.getAutor().getNome());
        this.autor.put("descricao", livro.getAutor().getDescricao());

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

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Map<String, Object> getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }
}
