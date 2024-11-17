package com.example.webapp.repository;

import com.example.webapp.entity.Patient;
import com.example.webapp.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatient(Patient patient);
}
