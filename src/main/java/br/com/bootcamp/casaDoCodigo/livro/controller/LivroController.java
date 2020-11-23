package br.com.bootcamp.casaDoCodigo.livro.controller;

import br.com.bootcamp.casaDoCodigo.livro.controller.dto.LivroDetalheDto;
import br.com.bootcamp.casaDoCodigo.livro.controller.dto.LivroDto;
import br.com.bootcamp.casaDoCodigo.livro.controller.dto.NovoLivroForm;
import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
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
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listar(){
        List livros = entityManager.createQuery("SELECT l from Livro l").getResultList();
        List<LivroDto> livrosDto = LivroDto.converter(livros);
        return ResponseEntity.ok(livrosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id){
        Livro livro = entityManager.find(Livro.class,id);
        if(livro == null){
            return ResponseEntity.notFound().build();
        }
        LivroDetalheDto livroDetalhe = new LivroDetalheDto(livro);
        return ResponseEntity.ok(livroDetalhe);
    }


}
