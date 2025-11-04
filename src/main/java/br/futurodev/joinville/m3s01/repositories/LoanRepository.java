package br.futurodev.joinville.m3s01.repositories;

import br.futurodev.joinville.m3s01.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
