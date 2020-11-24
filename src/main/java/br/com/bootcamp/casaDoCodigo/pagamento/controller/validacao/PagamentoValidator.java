package br.com.bootcamp.casaDoCodigo.pagamento.controller.validacao;

import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
import br.com.bootcamp.casaDoCodigo.pagamento.controller.dto.NovoPagamentoPrimeiraEtapaForm;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PagamentoValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoPagamentoPrimeiraEtapaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoPagamentoPrimeiraEtapaForm novoPagamentoPrimeiraEtapaForm = (NovoPagamentoPrimeiraEtapaForm) target;
        Pais pais = entityManager.find(Pais.class, novoPagamentoPrimeiraEtapaForm.getPais());

        Estado estado = null;

        if (novoPagamentoPrimeiraEtapaForm.getEstado() != null) {
            estado = entityManager.find(Estado.class, novoPagamentoPrimeiraEtapaForm.getEstado());

        }
        if (pais.possuiEstados() && !estado.pertenceAoPais(pais)) {
            errors.rejectValue("estado", null, "O estado é obrigatorio e deve corresponder ao país selecionado");
        }


        if (!novoPagamentoPrimeiraEtapaForm.documentoValido()) {
            errors.rejectValue("documento", null, "Cpf/Cnpj inválido");

        }


    }
}
