package com.hashini.medicare.service;

import com.hashini.medicare.model.Patient;
import com.hashini.medicare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients(Optional<String> patientName) {
        return patientName.map(patientRepository::findByNameIgnoreCaseStartsWith).orElseGet(patientRepository::findAll);
    }

    public Patient getPatient(long id) {
        return patientRepository.findById(id).get();
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}