package br.com.bootcamp.casaDoCodigo.cupom.model.Cupom;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
public class CupomAplicado {
    @ManyToOne
    private Cupom cupom;
    private BigDecimal percentualNoMomento;

    /**
     * Constrututor usada apenas pelo hibernate
     */
    @Deprecated
    public CupomAplicado(){}

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualNoMomento = cupom.getPercentualDesconto();
    }

    public BigDecimal getPercentualNoMomento() {
        return percentualNoMomento;
    }
}
