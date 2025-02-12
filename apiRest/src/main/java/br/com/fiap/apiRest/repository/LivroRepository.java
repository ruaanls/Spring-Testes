package br.com.fiap.apiRest.repository;

import br.com.fiap.apiRest.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>
{

}
