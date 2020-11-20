package br.com.bootcamp.casaDoCodigo.compartilhado.validacao;

import org.springframework.util.StringUtils;

public class ErroFormulario {

    private String campo;
    private String mensagem;

    public ErroFormulario(String campo, String mensagem){
        this.campo = campo;
        this.mensagem = mensagem;
    }


    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
