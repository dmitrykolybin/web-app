package com.example.webapp.repository;

import com.example.webapp.entity.Prescription;
import com.example.webapp.entity.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine, Long> {
    List<PrescriptionMedicine> findByPrescription(Prescription prescription);
}
