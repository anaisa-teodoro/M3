package br.futurodev.joinville.m3s01.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 50)
    private String document;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
