package br.com.bootcamp.casaDoCodigo.cupom.model.Cupom;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Cupom {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String codigo;

    @NotNull
    @Positive
    private BigDecimal percentualDesconto;

    @Future
    @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy@HH:mm:ss")
    private LocalDateTime validade;

    /**
     * Construtor usado pelo hibernate
     */
    @Deprecated
    public Cupom(){}

    public Cupom(@NotBlank String codigo, @NotNull @Positive BigDecimal percentualDesconto,
                 @Future @NotNull LocalDateTime validade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }

    public Boolean estaValido(){
        LocalDateTime dataAtual = LocalDateTime.now();
        return  dataAtual.compareTo(validade) <=0;
    }

    public UUID getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDateTime getValidade() {
        return validade;
    }
}
