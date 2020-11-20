package br.com.bootcamp.casaDoCodigo.categoria.controller.validator;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.categoria.controller.dto.NovaCategoriaForm;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.categoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CategoriaUnicaValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        NovaCategoriaForm novaCategoriaForm = (NovaCategoriaForm) target;
        Optional<Categoria> optionalCategoria = categoriaRepository.findByNome(novaCategoriaForm.getNome());
        optionalCategoria.ifPresent(categoria->{
            errors.rejectValue("nome",null,"A categoria informada ja está cadastrada");
        });

    }
}
