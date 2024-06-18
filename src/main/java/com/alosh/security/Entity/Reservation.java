package com.alosh.security.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "reservation")
    private Invoice invoice;

    // Getters and setters
}
