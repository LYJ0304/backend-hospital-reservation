package com.example.hospitalreservation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime reservationTime;

    private String status; // RESERVED, CANCELLED

    private String cancellationReason;
    private LocalDateTime canceledAt;

    protected Reservation() {}

    public Reservation(Doctor doctor, Patient patient, LocalDateTime reservationTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.reservationTime = reservationTime;
        this.status = "RESERVED";
    }

    public static Reservation of(Doctor doctor, Patient patient, LocalDateTime reservationTime) {
        return new Reservation(doctor, patient, reservationTime);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public LocalDateTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalDateTime reservationTime) { this.reservationTime = reservationTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }
    public LocalDateTime getCanceledAt() { return canceledAt; }
    public void setCanceledAt(LocalDateTime canceledAt) { this.canceledAt = canceledAt; }
}