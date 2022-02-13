package com.emelyan.service;

import com.emelyan.model.Doctor;
import com.emelyan.model.Patient;
import com.emelyan.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll(){return doctorRepository.findAll();}

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public Doctor getOne(Long id){return doctorRepository.findById(id).get();}

    public void update(Doctor updateDoctor,Long id){
        Long personId = doctorRepository.findById(id).get().getPerson().getId();
        updateDoctor.getPerson().setId(personId);
        doctorRepository.save(updateDoctor);
    }
    public void delete(Long id){ doctorRepository.deleteById(id);}

}
