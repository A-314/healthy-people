package com.emelyan.services;

import com.emelyan.models.Treatment;
import com.emelyan.repositories.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public void save(Treatment treatment){treatmentRepository.save(treatment);}

    public List<Treatment> findAll(){return treatmentRepository.findAll(Sort.by("date","id"));}

    public Treatment getOne(Long id){return  treatmentRepository.getOne(id);}

    public void update(Treatment updateTreatment){treatmentRepository.save(updateTreatment);}
}
