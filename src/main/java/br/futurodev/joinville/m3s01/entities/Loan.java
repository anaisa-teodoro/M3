package br.futurodev.joinville.m3s01.entities;

import br.futurodev.joinville.m3s01.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDate lentDate = LocalDate.now();

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(length = 10, nullable = false)
    private LoanStatus status = LoanStatus.PENDING;

    @Column(nullable = false)
    private Integer bookQuantity;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanBook> items = new ArrayList<>();

}
