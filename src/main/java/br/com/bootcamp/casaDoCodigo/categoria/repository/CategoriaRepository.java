package br.com.bootcamp.casaDoCodigo.categoria.repository;

import br.com.bootcamp.casaDoCodigo.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    Optional<Categoria> findByNome(String nome);
}
