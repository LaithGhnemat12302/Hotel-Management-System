package com.alosh.security.Entity;


import jakarta.persistence.*;

@Entity
public class HousekeepingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedEmployee;

    // Getters and setters
}
