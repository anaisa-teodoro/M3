package br.futurodev.joinville.m3s01.repositories;

import br.futurodev.joinville.m3s01.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
