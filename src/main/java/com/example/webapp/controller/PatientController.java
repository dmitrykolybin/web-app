package com.example.webapp.controller;

import com.example.webapp.entity.Doctor;
import com.example.webapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.webapp.entity.Patient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"patient", "doctor"})
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient")
    public String findPatient(@RequestParam("patientName") String patientName, Model model) {
        Patient patient = patientRepository.findByFullNameContaining(patientName)
                .orElseThrow(() -> new IllegalStateException("Patient not found"));
        Doctor doctor = (Doctor) model.getAttribute("doctor");

        model.addAttribute("patient", patient);
        model.addAttribute("doctor", doctor);
        return "patient";
    }

    @ModelAttribute("patient")
    public Patient getPatient() {
        return new Patient();
    }
}
