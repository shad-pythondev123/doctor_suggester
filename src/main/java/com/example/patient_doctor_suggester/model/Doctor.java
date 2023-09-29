package com.example.patient_doctor_suggester.model;

import com.example.patient_doctor_suggester.Enum.DoctorCity;
import com.example.patient_doctor_suggester.Enum.Speciality;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;





@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int Id;
   @Column(nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    DoctorCity doctorCity;
@Enumerated(EnumType.STRING)
    Speciality speciality;


    @Column(unique = true)
    String email;
    @Column(unique = true)
    String phone;
}
