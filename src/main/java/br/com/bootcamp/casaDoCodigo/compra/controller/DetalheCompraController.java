package br.com.bootcamp.casaDoCodigo.compra.controller;


import br.com.bootcamp.casaDoCodigo.compra.controller.dto.DetalheCompraDto;
import br.com.bootcamp.casaDoCodigo.compra.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("compra")
public class DetalheCompraController {

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> detalhes(@PathVariable Long id ){
        Compra compra = entityManager.find(Compra.class,id);
        if(compra==null){
            return ResponseEntity.notFound().build();
        }
        DetalheCompraDto detalheCompra = new DetalheCompraDto(compra);
        return ResponseEntity.ok(detalheCompra);
    }
}
