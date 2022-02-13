package com.emelyan.service;

import com.emelyan.model.Patient;
import com.emelyan.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void save(Patient patient){
        patientRepository.save(patient);
      }

    public void update(Patient updatePatient,Long id){
        Long personId = patientRepository.findById(id).get().getPerson().getId();
        updatePatient.getPerson().setId(personId);
        patientRepository.save(updatePatient);
    }
    public Patient show(Long id){
        return patientRepository.findById(id).get();
    }

    public void delete(Long id){patientRepository.deleteById(id);}
}
