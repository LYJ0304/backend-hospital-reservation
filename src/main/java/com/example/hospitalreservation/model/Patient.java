package com.example.hospitalreservation.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    protected Patient() {}

    public Patient(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setPatient(this);
    }
}
