package br.com.bootcamp.casaDoCodigo.compra.controller;

import br.com.bootcamp.casaDoCodigo.compra.controller.dto.NovaCompraForm;
import br.com.bootcamp.casaDoCodigo.compra.controller.validacao.CompraValidator;
import br.com.bootcamp.casaDoCodigo.compra.model.Compra;
import br.com.bootcamp.casaDoCodigo.cupom.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/compra")
public class RealizaCompraController {


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private CompraValidator compraValidator;

    @Autowired
    private CupomRepository cupomRepository;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(compraValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> compra(@RequestBody @Valid NovaCompraForm novaCompraForm, UriComponentsBuilder uriBuider) {
        Compra compra = novaCompraForm.paraCompra(entityManager,cupomRepository);
        entityManager.persist(compra);
        URI uri = uriBuider.path("/compras/{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }




}
