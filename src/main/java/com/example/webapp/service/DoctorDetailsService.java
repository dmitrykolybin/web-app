package com.example.webapp.service;

import com.example.webapp.entity.Doctor;
import com.example.webapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));

        return User.builder()
                .username(doctor.getLogin())
                .password(doctor.getPassword())
                .roles("DOCTOR")
                .build();
    }
}
