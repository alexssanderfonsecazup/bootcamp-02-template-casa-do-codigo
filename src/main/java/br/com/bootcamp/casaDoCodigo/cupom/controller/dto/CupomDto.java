package br.com.bootcamp.casaDoCodigo.cupom.controller.dto;

import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CupomDto {

    private UUID id;
    private LocalDateTime validade;
    private BigDecimal percentualDesconto;

    public CupomDto(Cupom cupom){
        this.id = cupom.getId();
        this.validade = cupom.getValidade();
        this.percentualDesconto = cupom.getPercentualDesconto();

    }


    public UUID getId() {
        return id;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }
}
