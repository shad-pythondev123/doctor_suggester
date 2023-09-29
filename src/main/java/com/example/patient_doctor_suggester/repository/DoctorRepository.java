package com.example.patient_doctor_suggester.repository;

import com.example.patient_doctor_suggester.Enum.DoctorCity;
import com.example.patient_doctor_suggester.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
public Doctor findByEmail(String email);
//public ArrayList<Doctor> findByCity(DoctorCity city);
}
