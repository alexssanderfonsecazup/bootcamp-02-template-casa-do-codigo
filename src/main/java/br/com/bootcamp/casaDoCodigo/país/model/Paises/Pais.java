package br.com.bootcamp.casaDoCodigo.país.model.Paises;

import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "nome_unico", columnNames = {"nome"})})
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "pais")
    @JsonIgnore
    private Set<Estado> estados;

    /**
     * Este construtor deve ser usado apenas pelo hibernate
     */
    @Deprecated
    public Pais(){}

    public Pais(@NotBlank String nome) {
        Assert.state(nome!=null, "O nome é obrigatório");
        this.nome = nome;
    }

    public boolean possuiEstados(){
        return estados.size()>=1;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public Set<Estado> getEstados() {
        return estados;
    }
}
