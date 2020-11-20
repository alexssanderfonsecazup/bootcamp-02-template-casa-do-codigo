package br.com.bootcamp.casaDoCodigo.livro.repository;

import br.com.bootcamp.casaDoCodigo.livro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
}
