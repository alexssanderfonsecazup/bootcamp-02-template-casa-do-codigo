package br.com.bootcamp.casaDoCodigo.autor.controller;

import br.com.bootcamp.casaDoCodigo.autor.controller.dto.NovoAutorForm;
import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import br.com.bootcamp.casaDoCodigo.autor.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    AutorRepository autorRepository;


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoAutorForm autorForm){
        Autor autor = autorForm.paraAutor(autorForm);
        autorRepository.save(autor);
        return ResponseEntity.ok(autor);
    }
}
