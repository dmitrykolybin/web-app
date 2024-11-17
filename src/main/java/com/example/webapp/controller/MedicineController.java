package com.example.webapp.controller;

import com.example.webapp.entity.Medicine;
import com.example.webapp.repository.MedicineRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/medicine_catalog")
    public String medicineCatalog(Model model) {
        List<Medicine> medicines = medicineRepository.findAll();
        model.addAttribute("all_medicines", medicines);
        return "medicine_catalog";
    }
}
