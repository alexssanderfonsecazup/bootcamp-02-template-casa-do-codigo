package br.com.bootcamp.casaDoCodigo.categoria.controller;


import br.com.bootcamp.casaDoCodigo.categoria.controller.dto.NovaCategoriaForm;
import br.com.bootcamp.casaDoCodigo.categoria.controller.validator.CategoriaUnicaValidator;
import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import br.com.bootcamp.casaDoCodigo.categoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaUnicaValidator categoriaUnicaValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(categoriaUnicaValidator);
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoriaForm novaCategoriaForm){

        Categoria categoria = novaCategoriaForm.paraCategoria();
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(categoria);
    }
}


