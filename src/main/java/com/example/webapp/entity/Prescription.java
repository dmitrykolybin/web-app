package com.example.webapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prescription")
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "remarks")
    private String remarks;

}
