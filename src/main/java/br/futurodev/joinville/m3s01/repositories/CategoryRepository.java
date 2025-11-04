package br.futurodev.joinville.m3s01.repositories;

import br.futurodev.joinville.m3s01.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
