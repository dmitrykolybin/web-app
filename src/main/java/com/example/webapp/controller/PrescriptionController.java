package com.example.webapp.controller;

import com.example.webapp.entity.Patient;
import com.example.webapp.entity.Prescription;
import com.example.webapp.entity.PrescriptionMedicine;
import com.example.webapp.repository.PrescriptionMedicineRepository;
import com.example.webapp.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"patient", "doctor"})
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    @GetMapping("/view_prescription")
    public String findPrescription(@RequestParam("prescriptionId") Long prescriptionId, Model model) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new IllegalStateException("Patient not found"));
        Patient patient = (Patient) model.getAttribute("patient");
        List<PrescriptionMedicine> prescriptionMedicines = prescriptionMedicineRepository
                .findByPrescriptionId(prescriptionId);

        model.addAttribute("prescription", prescription);
        model.addAttribute("prescription_medicines", prescriptionMedicines);
        model.addAttribute("patient", patient);
        return "view_prescription";
    }
}
