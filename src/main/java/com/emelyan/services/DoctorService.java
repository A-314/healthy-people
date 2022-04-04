package com.emelyan.services;

import com.emelyan.models.Doctor;
import com.emelyan.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> doctorList(String filter){
        if(filter!=null && !filter.isEmpty()){
            return doctorRepository.findDoctorsWithFilter(filter);
        }else {return doctorRepository.findAll();}
    }

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public Doctor getOne(Long id){return doctorRepository.findById(id).get();}

    public void update(Doctor updateDoctor,Long id){
        Integer personId = doctorRepository.findById(id).get().getPerson().getId();
        updateDoctor.getPerson().setId(personId);
        doctorRepository.save(updateDoctor);
    }
    public void delete(Long id){ doctorRepository.deleteById(id);}


}
