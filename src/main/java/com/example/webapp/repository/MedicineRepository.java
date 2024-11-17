package com.example.webapp.repository;

import com.example.webapp.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);
}
