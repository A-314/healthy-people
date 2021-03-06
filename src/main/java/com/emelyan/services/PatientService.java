package com.emelyan.services;

import com.emelyan.models.Patient;
import com.emelyan.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> patientList(String filter) {
        if(filter!=null && !filter.isEmpty()){
            return patientRepository.findPatientsWithFilter(filter);
        }else {return patientRepository.findAll();}
    }

    public List<Patient> patientList() {
     return patientRepository.findAll();
    }

    public void save(Patient patient){
        patientRepository.save(patient);
    }

    public void update(Patient updatePatient,Long id){
        Integer personId = patientRepository.findById(id).get().getPerson().getId();
        updatePatient.getPerson().setId(personId);
        patientRepository.save(updatePatient);
    }

    public Patient show(Long id){
        return patientRepository.findById(id).get();
    }

    public void delete(Long id){patientRepository.deleteById(id);}
}