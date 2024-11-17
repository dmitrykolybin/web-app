package com.example.webapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescription_medicine")
@Getter
@Setter
public class PrescriptionMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "frequency", nullable = false)
    private String frequency;

    @Column(name = "duration", nullable = false)
    private String duration;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;
}
