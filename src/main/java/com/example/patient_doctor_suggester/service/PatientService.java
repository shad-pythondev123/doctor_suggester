package com.example.patient_doctor_suggester.service;

import com.example.patient_doctor_suggester.model.Patient;
import com.example.patient_doctor_suggester.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public String addPatient(Patient patient) {
//        Patient p1= patientRepository.findByEmail(patient.getEmail());
        Optional<Patient> p1 = patientRepository.findByEmail(patient.getEmail());
        if (p1.isPresent()) return "Patient already Added!";
        else {
            if (patient.getName().length() < 3) return "Enter a valid name";
            else if (patient.getPhone().length() < 10) return "Enter a valid phone number";
            else if (patient.getCity().length() > 20) return "Enter a valid City";
            else {
                patientRepository.save(patient);
                return "Patient added successfully";
            }
        }
    }
        public String deletePatient(int id){
            Optional<Patient> p= patientRepository.findById(id);
            if(p.isPresent()){
                patientRepository.delete(p.get());
                return "Patient deleted Successfully";
            }
            else{
                return "Patient not Found";
            }
        }
    }

