package com.example.webapp.controller;

import com.example.webapp.entity.Doctor;
import com.example.webapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String login = authentication.getName();
        Doctor doctor = doctorRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalStateException("Doctor not found"));

        model.addAttribute("doctor", doctor);
        return "dashboard";
    }
}
