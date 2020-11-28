package br.com.bootcamp.casaDoCodigo.país.controller;


import br.com.bootcamp.casaDoCodigo.país.controller.dto.NovoPaisesForm;
import br.com.bootcamp.casaDoCodigo.país.model.Paises.Pais;
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
@RequestMapping("/paises")
public class PaisController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoPaisesForm paisesDto){
        Pais pais = new Pais(paisesDto.getNome());
        entityManager.persist(pais);
        return ResponseEntity.ok().build();

    }



}
