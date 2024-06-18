package com.alosh.security.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;

    @OneToMany(mappedBy = "assignedEmployee")
    private List<HousekeepingTask> tasks;

    // Getters and setters
}