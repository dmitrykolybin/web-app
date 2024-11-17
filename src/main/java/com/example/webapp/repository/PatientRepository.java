package com.example.webapp.repository;

import com.example.webapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByFullNameContaining(String fullName);
}
