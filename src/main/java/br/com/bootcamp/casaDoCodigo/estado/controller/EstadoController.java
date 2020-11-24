package br.com.bootcamp.casaDoCodigo.estado.controller;

import br.com.bootcamp.casaDoCodigo.estado.controller.dto.NovoEstadoForm;
import br.com.bootcamp.casaDoCodigo.estado.model.Estado;
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
@RequestMapping("/estado")
public class EstadoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoEstadoForm novoEstadoForm){
        Estado estado = novoEstadoForm.toEstado(entityManager);
        entityManager.persist(estado);
        return ResponseEntity.ok().build();

    }
}
