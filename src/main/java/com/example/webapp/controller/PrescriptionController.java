package com.example.webapp.controller;

import com.example.webapp.entity.Doctor;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@SessionAttributes({"patient", "doctor", "prescription", "new_prescription"})
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    private static String generateRandomString() {
        int length = 16;
        String allowedChars = "0123456789abcdefghijklmnopqrstuvwxyz";

        // Создаем объект для генерации случайных чисел
        Random random = new Random();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // Выбираем случайный символ из допустимых
            char randomChar = allowedChars.charAt(random.nextInt(allowedChars.length()));

            // Добавляем выбранный символ к строке
            sb.append(randomChar);
        }

        return sb.toString();
    }

    @GetMapping("/find_prescription")
    public String findPrescription(@RequestParam("prescriptionId") Long prescriptionId, Model model) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new IllegalStateException("Patient not found"));
        List<PrescriptionMedicine> prescriptionMedicines = prescriptionMedicineRepository
                .findByPrescription(prescription);

        model.addAttribute("prescription", prescription);
        model.addAttribute("prescription_medicines", prescriptionMedicines);
        return "view_prescription";
    }

    @GetMapping("/view_prescription")
    public String findPrescription(Model model) {
        Prescription prescription = (Prescription) model.getAttribute("prescription");
        List<PrescriptionMedicine> prescriptionMedicines = prescriptionMedicineRepository
                .findByPrescription(prescription);

        model.addAttribute("prescription_medicines", prescriptionMedicines);
        return "view_prescription";
    }

    @GetMapping("/activate_prescription")
    public String activatePrescription(Model model) {
        Prescription prescription = (Prescription) model.getAttribute("prescription");
        String sign = generateRandomString();
        prescription.setStatus("active");
        prescription.setSign(sign);
        prescriptionRepository.save(prescription);
        model.addAttribute("prescription", prescription);
        return "redirect:/view_prescription";
    }

    @GetMapping("/deactivate_prescription")
    public String deactivatePrescription(Model model) {
        Prescription prescription = (Prescription) model.getAttribute("prescription");
        prescription.setStatus("inactive");
        prescription.setSign("");
        prescriptionRepository.save(prescription);
        model.addAttribute("prescription", prescription);
        return "redirect:/view_prescription";
    }

    @GetMapping("/save_start_info")
    public String newPrescription(@RequestParam("diagnosis") String diagnosis, Model model) {
        Doctor doctor = (Doctor) model.getAttribute("doctor");
        Patient patient = (Patient) model.getAttribute("patient");

        Prescription prescription = new Prescription();
        prescription.setDiagnosis(diagnosis);
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescriptionRepository.save(prescription);

        model.addAttribute("new_prescription", prescription);

        return "redirect:/view_medicine_list";
    }

    @GetMapping("/complete_prescription")
    public String completePrescription(@RequestParam("remarks") String remarks, Model model) {
        Prescription prescription = (Prescription) model.getAttribute("new_prescription");
        prescription.setRemarks(remarks);
        prescription.setStatus("unassigned");
        prescription.setIssueDate(LocalDate.now().toString());
        prescription.setSign("");
        prescriptionRepository.save(prescription);
        model.addAttribute("prescription", prescription);
        return "redirect:/view_prescription";
    }
}
