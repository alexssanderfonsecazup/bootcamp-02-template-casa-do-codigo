package br.com.bootcamp.casaDoCodigo.cupom.controller;

import br.com.bootcamp.casaDoCodigo.cupom.controller.dto.CupomDto;
import br.com.bootcamp.casaDoCodigo.cupom.controller.dto.NovoCupomForm;
import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import br.com.bootcamp.casaDoCodigo.cupom.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    @Autowired
    CupomRepository cupomRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoCupomForm novoCupomForm){
        Cupom cupom = novoCupomForm.paraCupom();
        cupomRepository.save(cupom);
        return ResponseEntity.ok(new CupomDto(cupom));
    }
}
