package br.com.bootcamp.casaDoCodigo.autor.controller;

import br.com.bootcamp.casaDoCodigo.autor.controller.dto.NovoAutorForm;
import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @PersistenceContext
    EntityManager entityManager;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoAutorForm autorForm){
        Autor autor = autorForm.paraAutor(autorForm);
        entityManager.persist(autor);
        return ResponseEntity.ok().build();
    }
}
