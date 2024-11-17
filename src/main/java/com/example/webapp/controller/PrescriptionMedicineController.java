package com.example.webapp.controller;

import com.example.webapp.entity.*;
import com.example.webapp.repository.MedicineRepository;
import com.example.webapp.repository.PrescriptionMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"patient", "doctor", "new_prescription"})
public class PrescriptionMedicineController {
    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/view_medicine_list")
    public String viewMedicineList(Model model) {
        Prescription prescription = (Prescription) model.getAttribute("new_prescription");
        List<PrescriptionMedicine> medicineList = prescriptionMedicineRepository.findByPrescription(prescription);
        model.addAttribute("medicine_list", medicineList);

        return "view_medicine_list";
    }

    @GetMapping("/add_medicine_to_list")
    public String addMedicine(
            @RequestParam("medicine_name") String medicineName,
            @RequestParam("frequency") String frequency,
            @RequestParam("duration") String duration,
            Model model
    ) {
        Medicine medicine = medicineRepository.findByName(medicineName)
                .orElseThrow(() -> new IllegalStateException("Medicine not found"));
        Prescription prescription = (Prescription) model.getAttribute("new_prescription");

        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
        prescriptionMedicine.setMedicine(medicine);
        prescriptionMedicine.setPrescription(prescription);
        prescriptionMedicine.setFrequency(frequency);
        prescriptionMedicine.setDuration(duration);
        prescriptionMedicineRepository.save(prescriptionMedicine);

        return "redirect:/view_medicine_list";
    }
}
