package com.bank.locker.repository;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "lockers")
@Data
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Size size;

    private boolean availability;

    private String customer;

    // Getters and setters
}

