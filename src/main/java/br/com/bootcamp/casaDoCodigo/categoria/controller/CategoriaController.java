package br.com.bootcamp.casaDoCodigo.categoria.controller;


import br.com.bootcamp.casaDoCodigo.categoria.controller.dto.NovaCategoriaForm;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.categoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoriaForm novaCategoriaForm){

        Categoria categoria = novaCategoriaForm.paraCategoria();
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(categoria);
    }
}


