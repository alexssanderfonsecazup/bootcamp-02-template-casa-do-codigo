package br.com.bootcamp.casaDoCodigo.autor.controller.validacao;

import br.com.bootcamp.casaDoCodigo.autor.controller.dto.NovoAutorForm;
import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.autor.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class AutorEmailUnicoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        };
        NovoAutorForm novoAutorForm = (NovoAutorForm) target;
        Optional<Autor> optionalAutor = autorRepository.findByEmail(novoAutorForm.getEmail());
        optionalAutor.ifPresent(autor->{
           errors.rejectValue("email",null,"O email informado já está cadastrado");
        });
    }
}
