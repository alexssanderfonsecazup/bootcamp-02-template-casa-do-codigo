package br.com.bootcamp.casaDoCodigo.cupom.repository;

import br.com.bootcamp.casaDoCodigo.cupom.model.Cupom.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom,Long> {
    public Cupom getByCodigo(String codigo);
}
