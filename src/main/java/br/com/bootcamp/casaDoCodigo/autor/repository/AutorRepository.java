package br.com.bootcamp.casaDoCodigo.autor.repository;

import br.com.bootcamp.casaDoCodigo.autor.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByEmail(String email);
}
