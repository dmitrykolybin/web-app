package com.example.webapp.controller;

import com.example.webapp.entity.Prescription;
import com.example.webapp.repository.PatientRepository;
import com.example.webapp.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.webapp.entity.Patient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"patient", "doctor"})
public class PatientController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/find_patient")
    public String findPatient(@RequestParam("patientName") String patientName, Model model) {
        Patient patient = patientRepository.findByFullNameContaining(patientName)
                .orElse(null);
        if (patient == null) {
            return "no_patient_found";
        } else {
            model.addAttribute("patient", patient);
            return "redirect:/patient";
        }
    }

    @GetMapping("/patient")
    public String patientInfo(Model model) {
        Patient patient = (Patient) model.getAttribute("patient");
        List<Prescription> prescriptions = prescriptionRepository.findByPatient(patient);
        model.addAttribute("prescriptions", prescriptions);
        return "patient";
    }

    @GetMapping("/new_patient")
    public String newPatient(
            @RequestParam("fullName") String fullName,
            @RequestParam("gender") String gender,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("phoneNumber") String number,
            Model model
        ) {
        Patient patient = new Patient();
        patient.setFullName(fullName);
        patient.setGender(gender);
        patient.setBirthDate(birthDate);
        patient.setPhoneNumber(number);
        patientRepository.save(patient);
        model.addAttribute("patient", patient);
        return "redirect:/patient";
    }
}
