package br.futurodev.joinville.m3s01.repositories;

import br.futurodev.joinville.m3s01.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
