package br.futurodev.joinville.m3s01.repositories;

import br.futurodev.joinville.m3s01.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
