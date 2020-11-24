package br.com.bootcamp.casaDoCodigo.estado.model;

import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "nome_unico", columnNames = {"nome"})})
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @ManyToOne
    private Pais pais;

    /**
     * Este construtor deve ser usado apenas pelo hibernate
     */
    @Deprecated
    public Estado(){}

    public Estado(@NotNull Pais pais , @NotBlank String nome) {
        this.nome = nome;
        this.pais = pais;
    }

    public boolean pertenceAoPais(Pais pais) {

        if (this.pais.equals(pais)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return nome.equals(estado.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPaises() {
        return pais;
    }
}
