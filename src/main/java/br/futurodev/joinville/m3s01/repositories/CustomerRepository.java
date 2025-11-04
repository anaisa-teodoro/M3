package br.futurodev.joinville.m3s01.repositories;

import br.futurodev.joinville.m3s01.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
