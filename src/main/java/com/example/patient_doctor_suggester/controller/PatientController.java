package com.example.patient_doctor_suggester.controller;

import com.example.patient_doctor_suggester.model.Patient;
import com.example.patient_doctor_suggester.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/addPatient")
    public String addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam int id){
        return patientService.deletePatient(id);
    }

}
