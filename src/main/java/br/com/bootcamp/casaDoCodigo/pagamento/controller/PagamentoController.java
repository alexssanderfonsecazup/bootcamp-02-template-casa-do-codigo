package br.com.bootcamp.casaDoCodigo.pagamento.controller;

import br.com.bootcamp.casaDoCodigo.pagamento.controller.dto.NovoPagamentoPrimeiraEtapaForm;
import br.com.bootcamp.casaDoCodigo.pagamento.controller.validacao.PagamentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoValidator pagamentoValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(pagamentoValidator);
    }

    @PostMapping("primeiraEtapa")
    public ResponseEntity<?> pagamentoPrimeiraEtapa(@RequestBody @Valid NovoPagamentoPrimeiraEtapaForm primeiraEtapaPagamentoForm) {
        return ResponseEntity.ok(primeiraEtapaPagamentoForm);

    }
}
