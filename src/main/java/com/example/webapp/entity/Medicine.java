package com.example.webapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicine")
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "description", nullable = false)
    private String description;
}
