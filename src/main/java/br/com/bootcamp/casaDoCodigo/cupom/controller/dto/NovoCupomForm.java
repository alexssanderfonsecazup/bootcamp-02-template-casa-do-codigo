package br.com.bootcamp.casaDoCodigo.cupom.controller.dto;

import br.com.bootcamp.casaDoCodigo.compartilhado.validacao.Unique;
import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NovoCupomForm {

    @NotBlank @Unique(fieldName = "codigo", domainClass = Cupom.class)
    private String codigo;
    @NotNull @Positive
    private BigDecimal percentualDesconto;
    @Future
    @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy@HH:mm:ss")
    private LocalDateTime validade;

    public Cupom paraCupom() {
        return new Cupom(codigo,percentualDesconto,validade);
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
