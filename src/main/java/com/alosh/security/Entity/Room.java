package com.alosh.security.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String status;
    private double price;
    private int capacity;
    private String features;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    // Getters and setters
}
