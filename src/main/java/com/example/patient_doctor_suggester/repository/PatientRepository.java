package com.example.patient_doctor_suggester.repository;

import com.example.patient_doctor_suggester.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Optional<Patient> findByEmail(String email);
}
