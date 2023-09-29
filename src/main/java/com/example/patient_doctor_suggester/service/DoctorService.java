package com.example.patient_doctor_suggester.service;

import com.example.patient_doctor_suggester.Enum.DoctorCity;
import com.example.patient_doctor_suggester.Enum.Speciality;
import com.example.patient_doctor_suggester.Enum.Symptom;
import com.example.patient_doctor_suggester.model.Doctor;
import com.example.patient_doctor_suggester.model.Patient;
import com.example.patient_doctor_suggester.repository.DoctorRepository;
import com.example.patient_doctor_suggester.repository.PatientRepository;
import com.example.patient_doctor_suggester.repository.patientDoctorMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    patientDoctorMapRepository repo;

    public String addDoctor(Doctor doctor) {
        Doctor doctor1 = doctorRepository.findByEmail(doctor.getEmail());
        if (doctor1 != null) {
            return "Doctor already Exists";
        } else {
            if(doctor.getName().length()<3) return "Name should be more than 3 chars";
            else if(doctor.getPhone().length()<10) return "Enter a valid phone number";
            else {
                doctorRepository.save(doctor);
                return "Doctor added successfully";
            }
        }
    }

    public String deleteDoctor(int patientId) {
        Optional<Doctor> d1 = doctorRepository.findById(patientId);
        if (d1.isPresent()) {
            doctorRepository.delete(d1.get());
            return "Doctor deleted Successfully";
        } else {
            return "Doctor not Found!";
        }
    }
    public DoctorCity convertStringToEnum(String str){

        if(str.equals("DELHI"))
            return DoctorCity.DELHI;
        if(str.equals("NOIDA"))
            return DoctorCity.NOIDA;
        if(str.equals("FARIDABAD"))
            return DoctorCity.FARIDABAD;

        return null;
    }
    public Speciality getDoctorSpecialityFromPatientSymptom(Symptom patientSymptom){
        if(patientSymptom.equals(Symptom.ARTHRITIS) || patientSymptom.equals(Symptom.BACK_PAIN) ||
                patientSymptom.equals(Symptom.TISSUE_INJURIES))
            return Speciality.ORTHOPEDIC;
        else if(patientSymptom.equals(Symptom.DYSMENORRHEA))
            return Speciality.GYNECOLOGY;
        else if(patientSymptom.equals(Symptom.SKIN_INFECTION) || patientSymptom.equals(Symptom.SKIN_BURN))
            return Speciality.DERMATOLOGY;
        else if(patientSymptom.equals(Symptom.EAR_PAIN))
            return Speciality.ENT;

        return null;
    }

    public ResponseEntity<Object> suggestDoctors(int patientId) {
        Optional<Patient> check = patientRepository.findById(patientId);

        if(check.isPresent())
        {
            Patient patient1 = check.get();


            DoctorCity patientLocation = convertStringToEnum(patient1.getCity());
            Symptom patientSymptom = patient1.getSymptom();

            List<Doctor> doctors = doctorRepository.findAll();

            List<Doctor> doctors1 = new ArrayList<>();

            for(Doctor d : doctors){
                if(d.getDoctorCity().equals(patientLocation)){
                    doctors1.add(d);
                }
            }

            if(doctors1.isEmpty())
            {
                return new ResponseEntity<>("We are still waiting to expand to your location", HttpStatus.NOT_FOUND);
            }
            else
            {
                Speciality doctorSpeciality = getDoctorSpecialityFromPatientSymptom(patientSymptom);

                List<Doctor> doctors2 = new ArrayList<>();

                for(Doctor doctor :  doctors1){
                    if(doctor.getSpeciality().equals(doctorSpeciality)){
                        doctors2.add(doctor);
                    }
                }

                if(doctors2.isEmpty()){
                    return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(doctors2, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>("Patient with id = " + patientId + " not present", HttpStatus.NOT_FOUND);
//        List<Doctor> suggestedDoctors = new ArrayList<>();
//        Patient p1= patientRepository.findById(patientId).get();
//
//
//            String patientSymptom = String.valueOf(p1.getSymptom());
//            String patientCity = p1.getCity();
//            List<Doctor> doctorsList = doctorRepository.findAll();
//            List<Doctor> doctors = new ArrayList<>();
//            for (Doctor d : doctorsList) {
//                String docCity = String.valueOf(d.getDoctorCity());
//                if (patientCity.equals(docCity)) {
//                    doctors.add(d);
//                }
//            }
//            if (doctorsList.isEmpty()) {
//                return new ResponseEntity<>("We are still waiting to expand to your location", HttpStatus.NOT_FOUND);
//            } else {
//                String speciality = repo.getPatientDoctorMap().get(p1.getSymptom());
//                for (Doctor d : doctorsList) {
//                    String docSpeciality = String.valueOf(d.getSpeciality());
//
//                    if (docSpeciality.equals(speciality)) suggestedDoctors.add(d);
//
//                }
//
//            }
//            if (suggestedDoctors.isEmpty()) {
//                return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(suggestedDoctors, HttpStatus.OK);

        }


    }

