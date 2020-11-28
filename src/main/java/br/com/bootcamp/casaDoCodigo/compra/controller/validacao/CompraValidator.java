package br.com.bootcamp.casaDoCodigo.compra.controller.validacao;

import br.com.bootcamp.casaDoCodigo.compra.controller.dto.NovaCompraForm;
import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import br.com.bootcamp.casaDoCodigo.cupom.repository.CupomRepository;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class CompraValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCompraForm novaCompraForm = (NovaCompraForm) target;
        Pais pais = entityManager.find(Pais.class, novaCompraForm.getPais());


        if (!novaCompraForm.documentoValido()) {
            errors.rejectValue("documento", null, "Cpf/Cnpj inválido");
        }

        if(novaCompraForm.getCodigoCupom() != null){
            Cupom cupom = cupomRepository.getByCodigo(novaCompraForm.getCodigoCupom());
            if(!cupom.estaValido()){
                errors.rejectValue("codigoCupom", null, "Este cupom não é mais valído");
            }

        }

        if (!novaCompraForm.possuiEstado() && pais.possuiEstados()) {
            errors.rejectValue("estado", null, "O estado é obrigatorio para o país selecionado");
            return;
        }else if(!novaCompraForm.possuiEstado()){
            return;
        }

        Estado estado = entityManager.find(Estado.class, novaCompraForm.getEstado());

        if (!estado.pertenceAoPais(pais)) {
            errors.rejectValue("estado", null, "O estado deve corresponder ao país selecionado");
        }




    }
}
