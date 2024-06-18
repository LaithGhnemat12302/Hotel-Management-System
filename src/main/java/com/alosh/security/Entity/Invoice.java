package com.alosh.security.Entity;

import jakarta.persistence.*;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    // Getters and setters
}
