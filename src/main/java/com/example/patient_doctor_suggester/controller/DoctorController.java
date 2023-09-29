package com.example.patient_doctor_suggester.controller;

import com.example.patient_doctor_suggester.model.Doctor;
import com.example.patient_doctor_suggester.repository.DoctorRepository;
import com.example.patient_doctor_suggester.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/addDoctor")
    public String addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/deleteDoctor")
        public String deleteDoctor(@RequestParam int id){
            return doctorService.deleteDoctor(id);
        }
        @GetMapping("/suggest-doctors")
    public ResponseEntity<Object> suggestDoctors(@RequestParam int patientId){
        return doctorService.suggestDoctors(patientId);

        }
    }


