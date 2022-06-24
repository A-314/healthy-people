package com.emelyan.services;

import com.emelyan.models.Therapy;
import com.emelyan.repositories.TherapyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TherapyService {

    private final TherapyRepository therapyRepository;

    public TherapyService(TherapyRepository therapyRepository) {
        this.therapyRepository = therapyRepository;
    }

    public List<Therapy> findAll(){return therapyRepository.findAll();}

    public void save(Therapy therapy){therapyRepository.save(therapy);}

    public void update(Therapy updateTherapy){therapyRepository.save(updateTherapy);}

    public Therapy show(Long id){return therapyRepository.findById(id).get();}

    public void delete(Long id){therapyRepository.deleteById(id);}
}
