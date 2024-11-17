package com.example.webapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
