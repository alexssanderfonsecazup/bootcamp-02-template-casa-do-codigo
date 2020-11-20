package br.com.bootcamp.casaDoCodigo.livro.controller;

import br.com.bootcamp.casaDoCodigo.livro.controller.dto.LivroDto;
import br.com.bootcamp.casaDoCodigo.livro.controller.dto.NovoLivroForm;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {


    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoLivroForm novoLivroForm){
        Livro livro = novoLivroForm.toLivro(entityManager);
        entityManager.persist(livro);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listar(){
        List<Livro> livros = entityManager.createQuery("SELECT l from Livro l").getResultList();
        return ResponseEntity.ok(livros);
    }
}
