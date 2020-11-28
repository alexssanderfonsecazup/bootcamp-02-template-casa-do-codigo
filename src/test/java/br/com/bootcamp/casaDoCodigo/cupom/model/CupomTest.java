package br.com.bootcamp.casaDoCodigo.cupom.model;

import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CupomTest {

    @ParameterizedTest
    @DisplayName("Verifica validade do cupom")
    @CsvSource({
            "0,true",
            "-20,true",
            "1,false",
            "20,false"
    })
    public void verificaSeCupomEstaValidoIntervaloEmSegs(long valor,boolean resultado){
        LocalDateTime dataAtual = LocalDateTime.now().plusSeconds(valor);
        Cupom cupom = new Cupom("TESTE", BigDecimal.valueOf(0.3),dataAtual);
        cupom.estaValido();

    }






}
