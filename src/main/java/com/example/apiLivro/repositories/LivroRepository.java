package com.example.apiLivro.repositories;

import com.example.apiLivro.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    Optional<Livro> findByIsbn(String isbn);
}
