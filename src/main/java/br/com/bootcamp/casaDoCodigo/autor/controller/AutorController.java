package br.com.bootcamp.casaDoCodigo.autor.controller;

import br.com.bootcamp.casaDoCodigo.autor.controller.dto.NovoAutorForm;
import br.com.bootcamp.casaDoCodigo.autor.controller.validacao.AutorEmailUnicoValidator;
import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.autor.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    private AutorEmailUnicoValidator autorEmailUnicoValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(autorEmailUnicoValidator);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoAutorForm autorForm){
        Autor autor = autorForm.paraAutor(autorForm);
        autorRepository.save(autor);
        return ResponseEntity.ok(autor);
    }
}
