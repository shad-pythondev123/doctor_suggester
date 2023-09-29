package com.example.patient_doctor_suggester.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class patientDoctorMapRepository {
    private Map<String, String> patientDoctorMap= new HashMap<>();



    private void setPatientDoctorMap(){
       patientDoctorMap.put("ARTHRITIS", "ORTHOPEDIC");
       patientDoctorMap.put("BACK_PAIN", "ORTHOPEDIC");
       patientDoctorMap.put("TISSUE_INJURIES", "ORTHOPEDIC");
       patientDoctorMap.put("DYSMENORRHEA", "GYNECOLOGY");
       patientDoctorMap.put("SKIN_INFECTION", "DERMATOLOGY");
       patientDoctorMap.put("SKIN_BURN", "DERMATOLOGY");
       patientDoctorMap.put("EAR_PAIN", "ENT");
   }

    public Map<String, String> getPatientDoctorMap() {
        return patientDoctorMap;
    }

}
